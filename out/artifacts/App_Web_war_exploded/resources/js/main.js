/**
 * Created by Master on 16/03/2017.
 */
$(document).ready(function(){

    $('.collapsible').collapsible();

    $(".modif-personne").click(function(e){

        e.stopPropagation();

        var btn = $(this);
        var modifDivP = btn.parent().siblings(".item-collapsible").find(".modif-personne-div");

        modifDivP.slideToggle("slow");

        var nom = btn.siblings(".personne-info-nom").text();
        var prenom = btn.siblings(".personne-info-prenom").text();
        var id =  btn.siblings("form").find("[name='idPersonne']").val();

        var modifForm = modifDivP.find("form");
        var inputNom = modifForm.find("[name='nom']");
        var inputPrenom = modifForm.find("[name='prenom']");

        inputNom.val(nom);
        inputPrenom.val(prenom);
    });

    $(".modif-item").click(function(e){

        var btn = $(this);
        var nom = btn.siblings(".item-info-nom").text();
        var type = btn.siblings(".item-info-type").text();
        var date = btn.siblings(".item-info-date").text();
        var id = btn.siblings("form").find("[name='idItem']").val();

        var modifForm = btn.parent().parent().siblings(".modif-div").find('form');
        var modifDiv = btn.parent().parent().siblings(".modif-div");

        modifDiv.slideDown(1000);

        var inputNom = modifForm.find("[name='nomItem']");
        var inputType = modifForm.find("[name='typeItem']");
        var inputDate = modifForm.find("[name='date']");
        var inputId = modifForm.find("[name='idItem']");

        inputNom.val(nom);
        inputType.val(type);
        inputDate.val(date);
        inputId.val(id);

    });





});
