package br.com.baiocchilousa.brewer.storage.s3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;

import br.com.baiocchilousa.brewer.storage.FotoStorage;
import net.coobird.thumbnailator.Thumbnails;

/**
 * Classe de manipulação das fotos no serviço de storage de arquivos S3 da AWS
 * @author leolo
 *
 */
@Profile("prod")
@Component
public class FotoStorageS3 implements FotoStorage {

	private static final Logger LOGGER = LoggerFactory.getLogger(FotoStorageS3.class); 
			
	private static final String BUCKET = "blbrewer";

	@Autowired
	private AmazonS3 amazonS3;

	@Override
	public String salvar(MultipartFile[] files) {
		String novoNome = null;
		if (files != null && files.length > 0) {
			MultipartFile arquivo = files[0];
			novoNome = renomearArquivo(arquivo.getOriginalFilename());

			try {
				// Permissão para salvar os arquivos no Servidor S3
				AccessControlList acl = new AccessControlList();
				acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);

				enviarFoto(novoNome, arquivo, acl);

				enviarThumbnail(novoNome, arquivo, acl);

			} catch (IOException e) {
				throw new RuntimeException("Erro salvando arquivo no S3", e);
			}
		}

		return novoNome;
	}

	
	@Override
	public byte[] recuperar(String foto) {
		InputStream is = amazonS3.getObject(BUCKET, foto).getObjectContent();
		try {
			return IOUtils.toByteArray(is);
		} catch (IOException e) {
			LOGGER.error("Não conseguiu recuparar a foto do S3", e);
		}
		return null;
	}

	@Override
	public byte[] recuperarThumbnail(String foto) {
		return recuperar(FotoStorage.THUMBNAIL_PREFIX + foto);
	}

	//Método que apaga a foto e o thumbnail no servidor S3
	@Override
	public void excluir(String foto) {
		amazonS3.deleteObjects(new DeleteObjectsRequest(BUCKET).withKeys(foto, THUMBNAIL_PREFIX + foto));
	}

	@Override
	public String getUrl(String foto) {
		if(!StringUtils.isEmpty(foto)) {
			return "https://s3-sa-east-1.amazonaws.com/blbrewer/" + foto;
		}
		return null;
	}

	private void enviarFoto(String novoNome, MultipartFile arquivo, AccessControlList acl) throws IOException {
		// Meta dados do arquivo de imagem
		ObjectMetadata metaData = new ObjectMetadata();
		metaData.setContentType(arquivo.getContentType());
		metaData.setContentLength(arquivo.getSize());
		
		amazonS3.putObject(new PutObjectRequest(BUCKET, novoNome, arquivo.getInputStream(), metaData)
				.withAccessControlList(acl));
	}

	private void enviarThumbnail(String novoNome, MultipartFile arquivo, AccessControlList acl) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		Thumbnails.of(arquivo.getInputStream()).size(40, 68).toOutputStream(os);
		byte[] array = os.toByteArray();
		InputStream is = new ByteArrayInputStream(array);
		
		// Meta dados do arquivo de imagem de thumbnail
		ObjectMetadata thumbMetaData = new ObjectMetadata();
		thumbMetaData.setContentType(arquivo.getContentType());
		thumbMetaData.setContentLength(array.length);
		
		amazonS3.putObject(new PutObjectRequest(BUCKET, THUMBNAIL_PREFIX + novoNome, is, thumbMetaData)
				.withAccessControlList(acl));
	}

}
