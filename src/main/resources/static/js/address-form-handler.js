document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");
    const saveButton = document.getElementById("saveButton");

    // Captura os valores iniciais dos campos
    const initialValues = Array.from(form.elements).reduce((values, element) => {
        if (element.name) values[element.name] = element.value;
        return values;
    }, {});

    // Verifica se há mudanças no formulário
    const checkChanges = () => {
        const hasChanges = Array.from(form.elements).some((element) => {
            return element.name && element.value !== initialValues[element.name];
        });

        // Exibe ou oculta o botão com base nas mudanças
        saveButton.style.display = hasChanges ? "block" : "none";
    };

    // Adiciona o evento de entrada (input) para todos os campos do formulário
    form.addEventListener("input", checkChanges);
});
