import com.bd.rspeng.alwaysDisplaySelectList.AlwaysDisplaySelectList

/**
* PARAMETERS
*/
def String customFieldName = "The name of the custom Select List field I want to always display"
def int scriptedFieldIndexId = <The ID # of the ScriptRunner scripted field>

/**
* LOGIC SECTION
*/
def scriptedFieldIndexIdAsString = ""+scriptedFieldIndexId
def myScriptedSelectList = new AlwaysDisplaySelectList(scriptedFieldIndexIdAsString,customFieldName,issue)
myScriptedSelectList.main()
/**
* END LOGIC SECTION
*/