$(document).ready(function() {
    $('.radio-label').mouseenter(function() {
        var title = $(this).attr('title');
        $(this).siblings('.alert-message').text(title).show();
    });

    $('.radio-label').mouseleave(function() {
        $(this).siblings('.alert-message').hide();
    });

    $('input[name="descrizioneImp"]').mouseenter(function() {
        var title = $(this).attr('title');
        $(this).siblings('.alert-message').text(title).show();
    });

    $('input[name="descrizioneImp"]').click(function() {
        if ($(this).hasClass('disabled-input')) {
            $(this).siblings('.alert-message').text("Modifica Abilitata!").show();
            $(this).removeClass('disabled-input').addClass('enabled-input').removeAttr('readonly').focus();
        }
    });

    $('button[name="submit"]').click(function(e) {
        e.preventDefault(); // Evito il comportamento predefinito del form
        var formStato = $(this).closest('tr').find('form[name="formStato"]');
        var formPalinsesto = $(this).closest('tr').find('form[name="formPalinsesto"]');
        //var form = $(this).closest('tr').find('form');
        var nuovaDescrizione = $(this).closest('tr').find('input[name="descrizioneImp"]').val();
        var idImpianto = formStato.find('input[name="id"]').val();
        var nuovoStato = formStato.find('input[name="stato"]:checked').val();
        var nuovoPalinsestoId = formPalinsesto.find('select[name="palinsesto"]').val();

        console.log({id: idImpianto, stato: nuovoStato, palinsestoId: nuovoPalinsestoId, descrizione: nuovaDescrizione});
        $.post('/updateImpianto', {id: idImpianto, stato: nuovoStato, palinsestoId: nuovoPalinsestoId, descrizione:nuovaDescrizione})
            .done(function(data) {
                // Gestisco la risposta positiva del server
                console.log("Risposta dal server:", data);
                // Controllo se i dati sono uguali a quelli precedenti!
                if (data === "SUCCESSO") {
                    $('#alertMessage').text('Aggiornamento completato con successo!');
                } else if (data === "DATI_PRECEDENTI") {
                    $('#alertMessage').text('I nuovi dati sono identici a quelli precedenti.');
                }
                $('#customAlert').show();
            })
            .fail(function(jqXHR, textStatus, errorThrown) {
                // Gestisco la risposta negativa del server
                console.error("Errore:", textStatus, errorThrown);
                $('#alertMessage').text('Si è verificato un errore durante l\'aggiornamento.');
                $('#customAlert').show();
            });

    });

    $('#showFormButton').click(function() {
        $('#addImpiantoModal').css('display', 'flex'); // Mostra il form modale
    });

    $('#closeFormButton').click(function() {
        resetFormFields();
        $('#addImpiantoModal').css('display', 'none'); // Nasconde il form modale
    });

    $('button[name="add"]').click(function(e) {
        e.preventDefault();
        var form = $("#addImpiantoForm");
        var descrizione= form.find('input[name="descrizione"]').val();
        var latitudine= form.find('input[name="latitudine"]').val();
        var longitudine= form.find('input[name="longitudine"]').val();
        var palinsestoId = form.find('select[name="palinsesto"]').val();
        var stato = form.find('select[name="stato"]').val();
        console.log({descrizione: descrizione, latitudine: latitudine, longitudine: longitudine,palinsestoId: palinsestoId, stato: stato});

        $.post('/addImpianto', {descrizione: descrizione, latitudine: latitudine, longitudine: longitudine, palinsestoId: palinsestoId, stato: stato})
            .done(function(data) {
                // Gestisco la risposta positiva del server
                console.log("Risposta dal server:", data);
                // Controllo se i dati sono uguali a quelli precedenti!
                if (data === "SUCCESSO") {
                    $('#alertMessage').text('Impianto inserito correttamente');
                } else if (data === "DATI_PRECEDENTI") {
                    $('#alertMessage').text('I nuovi dati sono identici a quelli precedenti.');
                }
                $('#addImpiantoModal').css('display', 'none');
                $('#customAlert').show();
            })
            .fail(function(jqXHR, textStatus, errorThrown) {
                // Gestisco la risposta negativa del server
                console.error("Errore:", textStatus, errorThrown);
                $('#alertMessage').text('Dati inseriti non validi.');
                $('#addImpiantoModal').css('display', 'none');
                $('#customAlert').show();
            });
    });

    $('button[name="delete"]').click(function(e) {
        e.preventDefault();
        var formStato = $(this).closest('tr').find('form[name="formStato"]');
        var idImpianto = formStato.find('input[name="id"]').val();

        console.log({idImpianto: idImpianto});

        $.post('/deleteImpianto', {idImpianto: idImpianto})
            .done(function(data) {
                // Gestisco la risposta positiva del server
                console.log("Risposta dal server:", data);
                // Controllo se i dati sono uguali a quelli precedenti!
                if (data === "SUCCESSO") {
                    $('#alertMessage').text('Impianto cancellato correttamente');
                }
                $('#customAlert').show();
            })
            .fail(function(jqXHR, textStatus, errorThrown) {
                // Gestisco la risposta negativa del server
                console.error("Errore:", textStatus, errorThrown);
                $('#alertMessage').text('Si è verificato un errore durante l\'aggiornamento.');
                $('#customAlert').show();
            });
    });

    let map, marker;

    function initializeMap() {
        map = L.map('map').setView([38.111119, 13.350087], 14);
        L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
            maxZoom: 19,
            attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
        }).addTo(map);

        map.on('click', function(e) {
            if (marker) {
                map.removeLayer(marker);
            }
            marker = L.marker(e.latlng).addTo(map);
            $('#latitudine').val(e.latlng.lat);
            $('#longitudine').val(e.latlng.lng);
            $('#mapModal').css('display', 'none');
        });
    }

    $('#openMapLink').click(function(event) {
        event.preventDefault(); // Impedisce al link di ricaricare la pagina
        $('#mapModal').css('display', 'flex');
        setTimeout(() => {
            map.invalidateSize();
        }, 100); // Delay per assicurarsi che la mappa sia visualizzata correttamente
    });

    $('#closeMapButton').click(function() {
        $('#mapModal').css('display', 'none');
    });

    function resetFormFields() {
        $('#latitudine').val('');
        $('#longitudine').val('');
        $('#descrizione').val(''); // Reset del campo descrizione
        if (marker) {
            map.removeLayer(marker);
            marker = null;
        }
    }

    // Inizializza la mappa al caricamento della pagina
    initializeMap();



    $('#okButton').click(function() {
        $('#customAlert').hide();
        location.href="/gestione"
    });
});



