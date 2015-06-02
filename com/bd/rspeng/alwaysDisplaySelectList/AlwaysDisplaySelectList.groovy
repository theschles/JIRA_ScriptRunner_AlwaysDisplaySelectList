package com.bd.rspeng.alwaysDisplaySelectList

import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.Issue
import com.atlassian.jira.issue.customfields.manager.OptionsManager
import com.atlassian.jira.issue.fields.CustomField

import org.apache.log4j.*
import groovy.util.logging.*

@Log4j
class AlwaysDisplaySelectList {

    final static SOURCE_DIR = './../../data/jira/scripts/com/bd/rspeng/alwaysDisplaySelectList'

    final static SCRIPTED_FIELD_INDEX_ID_PLACEHOLDER = "SCRIPTED_FIELD_INDEX_ID_PLACEHOLDER"
    final static AJAX_URL_PLACEHOLDER = "AJAX_URL_PLACEHOLDER"
    final static CUSTOM_FIELD_REST_NAME_PLACEHOLDER = "CUSTOM_FIELD_REST_NAME_PLACEHOLDER"
    final static MASQUERADER_ID_PLACEHOLDER = "MASQUERADER_ID_PLACEHOLDER"
    final static CUSTOM_FIELD_NAME_PLACEHOLDER = "CUSTOM_FIELD_NAME_PLACEHOLDER"

    final static OptionsManager optionsManager = ComponentAccessor.getComponent(OptionsManager.class)

    private String scriptedFieldIndexId = null
    private String customFieldName = null
    private Issue issue = null
    private CustomField customFieldObject = null
    private Object customFieldStringValue = null
    private long customFieldIndexID = 0
    private String customFieldRestName = null
    private String masqueraderID = null

    public AlwaysDisplaySelectList(String scriptedFieldIndexId, String customFieldName, Issue issue) {
        log.setLevel(org.apache.log4j.Level.INFO)
        this.scriptedFieldIndexId = scriptedFieldIndexId
        this.customFieldName = customFieldName
        this.issue = issue
        this.customFieldObject = ComponentAccessor.getCustomFieldManager().getCustomFieldObjectByName(customFieldName)
        this.customFieldStringValue = issue.getCustomFieldValue(customFieldObject)
        this.customFieldIndexID = customFieldObject.idAsLong
        this.customFieldRestName = new StringBuilder().append("customfield_").append(customFieldIndexID).toString()
        this.masqueraderID = new StringBuilder().append("injectedCustomFieldMasquerader_").append(customFieldIndexID).toString()
    }

    public main() {
        if (customFieldStringValue != null) {
            generateHideSelectListJavaScript()
        } else {
            generateMasqueradeSelectList()
        }
    }

    private String generateMasqueradeSelectList() {
        def StringBuilder output = new StringBuilder()
        output << '<script type="text/javascript">' << getMasqueradeSelectListJavaScript() << '</script>'
        output << '<select id="' << masqueraderID << '" onChange="updateCustomFieldTo()"><option value="None">None</option>'
        def fieldConfig = customFieldObject.getRelevantConfig(issue)
        def options = optionsManager.getOptions(fieldConfig)
        for (e in options) {
            output << '<option value="' << e.value << '">' << e.value << '</option>'
        }
        output << '</select>'
        output.toString()
    }

    private String getAJAXURL() {
        def baseURL = ComponentAccessor.getApplicationProperties().getString("jira.baseurl")
        new StringBuilder().append(baseURL).append("/rest/api/2/issue/").append(issue.key).toString()
    }

    private String generateHideSelectListJavaScript() {
        def fileContents = new File(SOURCE_DIR + "/hideSelectList.js").getText('UTF-8')
        fileContents = replaceScriptedFieldIndexIdPlaceholder(fileContents)
        new StringBuilder().append('<script type="text/javascript">').append(fileContents).append('</script>').toString()
    }

    private String getMasqueradeSelectListJavaScript() {
        def fileContents = new File(SOURCE_DIR + "/masqueradeSelectList.js").getText('UTF-8')
        fileContents = replaceMasqueradeSelectListPlaceholders(fileContents)
        fileContents
    }

    private String replaceMasqueradeSelectListPlaceholders(String fileContents) {
        fileContents = fileContents.replace(CUSTOM_FIELD_REST_NAME_PLACEHOLDER, customFieldRestName)
        fileContents = fileContents.replace(AJAX_URL_PLACEHOLDER, getAJAXURL())
        fileContents = fileContents.replace(MASQUERADER_ID_PLACEHOLDER, masqueraderID)
        fileContents = fileContents.replace(CUSTOM_FIELD_NAME_PLACEHOLDER, customFieldName)
        fileContents = replaceScriptedFieldIndexIdPlaceholder(fileContents)
        fileContents
    }

    private String replaceScriptedFieldIndexIdPlaceholder(String fileContents) {
        fileContents.replace(SCRIPTED_FIELD_INDEX_ID_PLACEHOLDER, scriptedFieldIndexId)
    }
}