var debug = true
AJS.toInit(function(){
    masqueradeScriptedFieldAsCustomField()    
});

function updateCustomFieldTo() {
    var dataObject = {
        "fields": {
            'CUSTOM_FIELD_REST_NAME_PLACEHOLDER':{"value":getMasqueraderSelectValue()}
        }
    };
    var data = JSON.stringify(dataObject)
    log("data for PUT: ",data)
    updateJIRA(data)

}

function getMasqueraderSelectValue() {
    var masqueraderSelectTag = document.getElementById('MASQUERADER_ID_PLACEHOLDER')
    log("masqueraderSelectTag",masqueraderSelectTag)
    var masqueraderSelectValue = masqueraderSelectTag.options[masqueraderSelectTag.selectedIndex].value
    return masqueraderSelectValue
}

function updateJIRA(data) {
    AJS.$.ajax({
            type: 'PUT',
            contentType: 'application/json',
            url: 'AJAX_URL_PLACEHOLDER',
            data: data,
            dataType: 'json',
            success: function () {
                log('Update success')
                window.location.reload()
            },
            error: function () {
                log('Update fail')
            }
    });
}

function log(message,data) {
    if (debug) {
        (data == undefined) ? console.log(message) : console.log(message,data)
    }
}

function masqueradeScriptedFieldAsCustomField() {
  AJS.$('#rowForcustomfield_SCRIPTED_FIELD_INDEX_ID_PLACEHOLDER > div > strong').text('CUSTOM_FIELD_NAME_PLACEHOLDER:')
}
