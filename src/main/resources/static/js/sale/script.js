function updateSummary() {
  let totalQuantity = 0;
  let totalValue = 0;

  // Select all checked checkboxes
  const checkboxes = document.querySelectorAll('.product-checkbox:checked');

  checkboxes.forEach((checkbox) => {
      const row = checkbox.closest('tr'); // Find the closest table row (tr)
      const quantity = parseInt(row.querySelector('input[name="quantity"]').value) || 0; // Get the quantity value
      const value = parseFloat(row.querySelector('td:nth-child(3)').textContent.replace(',', '.')) || 0; // Get the price value

      totalQuantity += quantity; // Add quantity to total quantity
      totalValue += quantity * value; // Add the total value to total value
  });

  // Update the total quantity and total value on the page
  document.getElementById('totalQuantity').textContent = totalQuantity;
  document.getElementById('totalValue').textContent = totalValue.toFixed(2); // Format the value to 2 decimal places

  // Update the hidden fields with the total values
  document.getElementById('hiddenTotalValue').value = totalValue.toFixed(2);
  document.getElementById('hiddenTotalQuantity').value = totalQuantity;
}

document.addEventListener('DOMContentLoaded', () => {
  // Add event listeners for when checkboxes change or quantities are inputted
  document.querySelectorAll('.product-checkbox').forEach(checkbox => {
      checkbox.addEventListener('change', updateSummary);
  });

  document.querySelectorAll('input[name="quantity"]').forEach(input => {
      input.addEventListener('input', updateSummary);
  });

  updateSummary(); // Update values when the page loads
});


$('form').submit(function(event) {
    event.preventDefault();

    var productIds = [];
    var quantities = [];

    $('.product-checkbox:checked').each(function() {
        productIds.push($(this).val());
        quantities.push($(this).closest('tr').find('input[name="quantity"]').val());
    });

    // Armazenar IDs e quantidades no campo oculto
    $('#productIds').val(productIds.join(','));
    $('#quantities').val(quantities.join(','));

    // Submete o formulário
    this.submit();
});
