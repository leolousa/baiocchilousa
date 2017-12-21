$(function() {
	var decimal = $('.js-decimal');
	decimal.maskMoney();
	
	var inteiro = $('.js-inteiro');
	inteiro.maskMoney({precision: 0});
});