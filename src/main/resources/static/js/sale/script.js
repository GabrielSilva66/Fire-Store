function updateSummary(){
    let totalQuantity = 0;
    let totalValue = 0;

    const chekbox = document.querySelectorAll('.product-checkbox:checked');

   checkboxes.forEach((checkbox) => {
        const quantity = parseInt(checkbox.closest('tr').querySelector('input[name="quantity"]').value);
        const value = parseFloat(checkbox.dataset.price);

        totalQuantity += quantity;
        totalValue += quantity * value;
    });

    document.getElementById('totalQuantity').textContent = totalQuantity;
    document.getElementById('totalValue').textContent = totalValue.toFixed(2);

        // Adicionar evento de mudança aos checkboxes
        document.querySelectorAll('.product-checkbox').forEach(checkbox => {
            checkbox.addEventListener('change', updateSummary);
        });
    
        // Chama a função uma vez para definir os valores iniciais
        updateSummary();
}