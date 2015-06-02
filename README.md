This is open-source out of the goodness of my company’s heart.  There is no warranty or guarantee express or implied.  Feel free to contact me if you have questions, but I cannot guarantee a response time.

Steps to install:

# In JIRA: install ScriptRunner plugin 
# In JIRA: create a custom Select List field
# In JIRA: create a ScriptRunner scripted field *of a different name than the field you created in Step 2*
# Find out the scripted field’s ID #: in the JIRA Administration > Issues screen, click Custom Fields, then click the gear next to the scripted field, then hover over the first option that appears; look in your browser’s status row (usually at the bottom of the browser) to see the ID # in the displayed URL
# Copy this GitHub repo’s entire directory structure (except for the README.md) into your JIRA scripts folder.  If you want to rearrange anything, note that you might need to modify the “package”, “import”, and “SOURCE_DIR” values.
# Edit the configurationScript.groovy file to enter in:
## The exact name of the custom Select List field
## The ID # of the scripted field
# In JIRA: configure the scripted field to use these files by going to Administration > Addons > Scripted Fields.  Click the Edit link for the scripted field you’ve created.  Put in the script field the /path/to/configurationScript.groovy (or D:\path\to\configurationScript.groovy).  Set the output to be HTML.  Click the Update button.
## Note that if you click the Preview button with an issue, if you change the value, the screen will refresh and your settings will be lost at this point.  So: click Update first, then go back and Preview.
# If all works well, add the custom Select List field and scripted field to the “Default” screen.

Good luck!