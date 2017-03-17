$(document).ready(function () {
});

function start() {
    PF('statusDialog').show();
}

function stop() {
    PF('statusDialog').hide();
}

function handleSubmitRequest(xhr, status, args, dialogId, formId, dialogWidgetVar) {
    if (args.validationFailed) {
        PF(dialogWidgetVar).jq.effect("shake", {times: 5}, 100);
    } else {
        clearForm(formId);
        PF(dialogWidgetVar).hide();
    }
}

function clearForm(formId) {
    jQuery('#' + formId).each(function () {
        this.reset();
    });
}

function getThumbnail(original, scale) {
    var canvas = document.createElement("canvas");

    canvas.width = original.width * scale;
    canvas.height = original.height * scale;

    canvas.getContext("2d").drawImage(original, 0, 0, canvas.width, canvas.height);

    return canvas;
}

function noWhiteSpacesAndMakeUpperCases(e) {
    if (event.keyCode == 32) {
        e.value = e.value.replace(/\s+/g, '');
        return false;
    }
    e.value = e.value.toUpperCase();
}

$(document).keydown(function (evt) {
    if (evt.keyCode == 83 && (evt.ctrlKey)) {
        evt.preventDefault();
        alert('CTRL-S');
    }
    if (evt.keyCode == 67 && (evt.ctrlKey)) {
        evt.preventDefault();
        alert('CTRL-C');
    }
    if (evt.keyCode == 112) {
        evt.preventDefault();
        alert('F1');
    }
});

/**
 * https://code.google.com/p/primefaces/wiki/PrimeFacesLocales
 */
PrimeFaces.locales['tr_TR'] = {
    closeText: 'Kapat',
    prevText: 'Geri',
    nextText: 'İleri',
    currentText: 'Bugün',
    monthNames: ['Ocak', 'Şubat', 'Mart', 'Nisan', 'Mayıs', 'Haziran', 'Temmuz', 'Ağustos', 'Eylül', 'Ekim', 'Kasım', 'Aralık'],
    monthNamesShort: ['Oca', 'Şub', 'Mar', 'Nis', 'May', 'Haz', 'Tem', 'Ağu', 'Eyl', 'Eki', 'Kas', 'Ara'],
    dayNames: ['Pazar', 'Pazartesi', 'Salı', 'Çarşamba', 'Perşembe', 'Cuma', 'Cumartesi'],
    dayNamesShort: ['Pz', 'Pt', 'Sa', 'Ça', 'Pe', 'Cu', 'Ct'],
    dayNamesMin: ['Pz', 'Pt', 'Sa', 'Ça', 'Pe', 'Cu', 'Ct'],
    weekHeader: 'Hf',
    firstDay: 1,
    isRTL: false,
    showMonthAfterYear: false,
    yearSuffix: '',
    month: 'Ay',
    week: 'Hafta',
    day: 'Gün',
    allDayText: 'Tüm Gün'
};
