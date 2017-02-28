This is open-source out of the goodness of my company’s heart.  There is no warranty or guarantee express or implied.  Feel free to contact me if you have questions, but I cannot guarantee a response time.

Steps to install:

1. In JIRA: install ScriptRunner plugin 
2. In JIRA: create a custom Select List field
3. In JIRA: create a ScriptRunner scripted field *of a different name than the field you created in Step 2*
4. Find out the scripted field’s ID #: in the JIRA Administration > Issues screen, click Custom Fields, then click the gear next to the scripted field, then hover over the first option that appears; look in your browser’s status row (usually at the bottom of the browser) to see the ID # in the displayed URL
5. Copy this GitHub repo’s entire directory structure (except for the README.md) into your JIRA scripts folder.  If you want to rearrange anything, note that you might need to modify the “package”, “import”, and “SOURCE_DIR” values.
6. Edit the configurationScript.groovy file to enter in:
  * The exact name of the custom Select List field
  * The ID # of the scripted field
7. In JIRA: configure the scripted field to use these files by going to Administration > Addons > Scripted Fields.  Click the Edit link for the scripted field you’ve created.  Put in the script field the /path/to/configurationScript.groovy (or D:\path\to\configurationScript.groovy).  Set the output to be HTML.  Click the Update button.
  * Note that if you click the Preview button with an issue and then change the value of the generated drop-down, the screen will refresh and your settings will be lost at this point (it will also alter the value of that issue's custom select list field).  So: click Update first, then go back and Preview.
8. If all works well, add the custom Select List field and scripted field to the “Default” screen.

Background:
In 2004 -- see https://jira.atlassian.com/browse/JRA-2997 -- Atlassian made the decision that JIRA would not display custom fields on the issue view screen if those custom fields had no set value / were empty. The only way to force a custom field to always display on the issue view screen was to set it to required and then force a default value.

One problem with setting a custom field to be required in JIRA is that it breaks Crucible / JIRA integration for custom Select List fields: errors are displayed when creating an issue in-line in Crucible and if those errors are ignored, there is a risk that JIRA might create orphan subtasks with no parent task attached.

For the past 11 years, JIRA users and administrators have been clamouring for Atlassian to change this situation; so far, Atlassian has put other features as higher priority to people's chagrin even though over 150 people voted for a fix on Atlassian's own JIRA tracker.

We ourselves discovered this situation at my company recently -- and it appeared no one as of yet had published a comprehensive solution. I wrote -- and then got company approval to open-source -- the above-linked mix of Groovy and JavaScript code that creates a ScriptRunner scripted field that, given a specified custom Select List field, will masquerade as that specified custom Select List field when that specified custom Select List field has no set value / is empty.

The ScriptRunner plugin for JIRA is free via the Atlassian Marketplace and this code is open-source. Please try this out and suggest improvements via the GitHub system.

Good luck!
