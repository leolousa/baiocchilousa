Brewer.TabelaItens = (function() {
	
	function TabelaItens(autocomplete) {
		this.autocomplete = autocomplete;
		this.tabelaCevejasContainer = $('.js-tabela-cervejas-container');
	}
	
	TabelaItens.prototype.iniciar = function() {
		this.autocomplete.on('item-selecionado', onItemSelecionado.bind(this));
	}
	
	
	function onItemSelecionado(evento, item) {
		//console.log('Item recebido do autocomplete', item);
		//Envia o código do item para o método mapeado com '/item' do controller VendasController
		var resposta = $.ajax({
			url: 'item',//Como estamos na URL podemos passar somente o 'item'
			method: 'POST',
			data: {
				codigoCerveja: item.codigo,
			}
		});
		
		resposta.done(onItemAdcionadoNoServidor.bind(this));
	}
	
	function onItemAdcionadoNoServidor(html) {
		this.tabelaCevejasContainer.html(html);
	}
	
	return TabelaItens;
	
	
}());

$(function() {
	var autocomplete = new Brewer.Autocomplete();
	autocomplete.iniciar();
	
	var tabelaItens = new Brewer.TabelaItens(autocomplete);
	tabelaItens.iniciar();
})