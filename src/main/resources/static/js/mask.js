$(document).ready(function(){
console.log("Mask");

    // Aplica a máscara de telefone no campo com id 'telephone'
    $('#telephone').mask('(00) 0000-0000');

    // Aplica a máscara de CEP no campo com id 'cep'
    $('#cep').mask('00000-000');

    // Aplica a máscara de CNPJ no campo com id 'cnpj', no formato "00.000.000/0000-00"
    $('#cnpj').mask('00.000.000/0000-00', {reverse: true});
});
