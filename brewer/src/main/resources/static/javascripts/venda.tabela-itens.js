Brewer.TabelaItens = (function() {
	
	function TabelaItens(autocomplete) {
		this.autocomplete = autocomplete;
		this.tabelaCevejasContainer = $('.js-tabela-cervejas-container');
		this.uuid = $('#uuid').val();
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	TabelaItens.prototype.iniciar = function() {
		this.autocomplete.on('item-selecionado', onItemSelecionado.bind(this));
		
		bindQuantidade.call(this);
		bindTabelaItem.call(this);
	}
	
	TabelaItens.prototype.valorTotal = function() {
		return this.tabelaCevejasContainer.data('valor');
	}
	
	
	function onItemSelecionado(evento, item) {
		//console.log('Item recebido do autocomplete', item);
		//Envia o código do item para o método mapeado com '/item' do controller VendasController
		var resposta = $.ajax({
			url: 'item',//Como estamos na URL podemos passar somente o 'item'
			method: 'POST',
			data: {
				codigoCerveja: item.codigo,
				uuid: this.uuid
			}
		});
		
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	
	function onItemAtualizadoNoServidor(html) {
		this.tabelaCevejasContainer.html(html);
		
		bindQuantidade.call(this);
		
		var tabelaItem = bindTabelaItem.call(this);
		
		this.emitter.trigger('tabela-itens-atualizada', tabelaItem.data('valor-total'));
	}
	
	function onQuantidadeItemAlterado(evento) {
		var input = $(evento.target);
		var quantidade = input.val();
		
		if(quantidade <= 0 ) {
			input.val(1);
			quantidade = 1;
		}
		
		var codigoCerveja = input.data('codigo-cerveja');
	
		//Envia a requisição para o servidor com o código da cerveja
		var resposta = $.ajax({
			url: 'item/' + codigoCerveja,
			method: 'PUT',
			data: {
				quantidade: quantidade,
				uuid: this.uuid
			}
		});
		
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	
	function onDoubleClick(evento) {
		//	var item = $(evento.currentTarget) é o ==> $(this);
		$(this).toggleClass('solicitando-exclusao');
	}
	
	function onExclusaoItemClick(evento) {
		var codigoCerveja = $(evento.target).data('codigo-cerveja');
		var resposta = $.ajax({
			url: 'item/' + this.uuid + '/' + codigoCerveja,
			method: 'DELETE'
		});
		
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	
	function bindQuantidade() {
		var quantidadeItemInput = $('.js-tabela-cerveja-quantidade-item');
		//bug plugin Normandes - quantidadeItemInput.on('change', onQuantidadeItemAlterado.bind(this));
		quantidadeItemInput.on('blur', onQuantidadeItemAlterado.bind(this));
		//quantidadeItemInput.maskMoney({ precision: 0, thousands: '' });
		quantidadeItemInput.maskNumber({ integer: true, thousands: '' });
	}
	
	function bindTabelaItem() {
		var tabelaItem = $('.js-tabela-item');
		tabelaItem.on('dblclick', onDoubleClick);
		$('.js-exclusao-item-btn').on('click', onExclusaoItemClick.bind(this));
		return tabelaItem;
	}
	
	return TabelaItens;
	
	
}());
