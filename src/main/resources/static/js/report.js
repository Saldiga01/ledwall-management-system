function generateReport() {
    let id = document.getElementById('idInserito').value;
    let startDate = document.getElementById('startDate').value;
    let endDate = document.getElementById('endDate').value;
    let errorMessage = document.getElementById('error-message');

    if (!id || !startDate || !endDate) {
        errorMessage.textContent = 'Per favore, compila tutti i campi richiesti.';
        return;
    }

    errorMessage.textContent = '';
    location.href = `/report?id=` + encodeURIComponent(id) +
                    '&startDate=' + encodeURIComponent(startDate) +
                    '&endDate=' + encodeURIComponent(endDate);

}
