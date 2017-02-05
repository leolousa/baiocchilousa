/**
 * Funções necessárias à Aplicação 
 */


/* Mantém dialogo aberto na verificação de campos obrigatórios*/
function verificar(xhr, status, args, dlg, tbl) {
    if(args.validationFailed) {
        PF(dlg).jq.effect("shake", {times:5}, 100);
    } else {
        PF(dlg).hide();
        PF(tbl).clearFilters();
    }
}