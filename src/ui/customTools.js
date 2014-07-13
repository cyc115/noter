function listProp(o) {
    var objectToInspect;
    var result = [];

    for (objectToInspect = o; objectToInspect !== null; objectToInspect = Object.getPrototypeOf(objectToInspect)) {
        result = result.concat(Object.getOwnPropertyNames(objectToInspect));
    }

    return result;
}

function onload() {
    firebug.env.debug = false;
    
}
var editor = CodeMirror.fromTextArea(document.getElementById("code"),
                                     
{
    lineNumbers: true,
    matchBrackets: true,
    mode: "text/x-markdown"
});
//var editor = CodeMirror.fromTextArea(document.getElementById("code"), {
//    lineNumbers: true,
//    tabMode : "indent",
//    matchBrackets: true,
//    lineWrapping: true, 
//    mode: "text/x-markdown"
//});
//used to wrap line 
//var charWidth = editor.defaultCharWidth(), basePadding = 4;
//    editor.on("renderLine", function(cm, line, elt) {
//    var off = CodeMirror.countColumn(line.text, null, cm.getOption("tabSize")) * charWidth;
//    elt.style.textIndent = "-" + off + "px";
//    elt.style.paddingLeft = (basePadding + off) + "px";
//});
//editor.refresh();