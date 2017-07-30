<%--
  Created by IntelliJ IDEA.
  User: Master
  Date: 14/03/2017
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Inscription</title>
        <link type="text/css" rel="stylesheet" href="form.css" />
        <link href="<c:url value="/resources/css/materialize.css" />" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    </head>
    <body>

        <div class="container">

            <div class="row center">
                <h4>Carnet de prêt</h4>
            </div>

            <div class="row my-row">

                <ul class="collapsible" data-collapsible="accordion">

                    <c:forEach items="${personnes}" var="personne">

                        <li>
                            <div class="collapsible-header collapsible-header-personne">
                                <i class="material-icons">account_circle</i>
                                <span class="info-personne personne-info-nom">${personne.getChild("nom").getText()}</span> - <span class="info-personne personne-info-prenom">${personne.getChild("prenom").getText()}</span>

                                <form  method="post" action="/" class="form-list">
                                    <input type="hidden" value="deletePersonne" name="fonction" />
                                    <input type="hidden" value="${personne.getAttribute("id").getValue()}" name="idPersonne" />
                                    <%--<input type="submit" value="Supprimer cette personne" class="sansLabel" />--%>
                                    <button class="btn waves-effect waves-light button-list-personne " type="submit" name="action">
                                        <i class="material-icons right icon-button-list-personne">clear</i>
                                    </button>
                                </form>

                                <button class="btn waves-effect waves-light button-list-personne modif-personne" type="submit" name="action">
                                    <i class="material-icons right icon-button-list-personne">mode_edit</i>
                                </button>

                            </div>
                            <div class="collapsible-body item-collapsible">

                                <%--adasdasdsad--%>

                                    <div class="row no-margin modif-personne-div">
                                        <form  method="post" action="/">

                                            <input type="hidden" value="updatePersonne" name="fonction" />
                                            <input type="hidden" value="${personne.getAttribute("id").getValue()}" name="idPersonne" />

                                            <div class="col s12 add-item-form-header">
                                                <div class="col s3 ajouter-pret-titre">
                                                    Modifier les infos de ${personne.getChild("nom").getText()} :
                                                </div>
                                                <div class=" col s4">
                                                    <input placeholder="Nom"  name="nom" type="text" class="validate input-item">
                                                </div>
                                                <div class="col s4">
                                                    <input placeholder="Prenom"  name="prenom" type="text" class="validate input-item">
                                                </div>

                                                <div class="col s1 col-add-item">
                                                    <button class="btn waves-effect waves-light button-list-item " type="submit" name="action">
                                                        <i class="material-icons right icon-button-list-item">done</i>
                                                    </button>
                                                </div>
                                            </div>

                                        </form>

                                    </div>
                                    <div class="divider"></div>


                                <%--asdasdasdaskjk--%>

                                <div class="row no-margin">
                                    <form  method="post" action="/">

                                        <input type="hidden" value="addItemToPersonne" name="fonction" />
                                        <input type="hidden" value="${personne.getAttribute("id").getValue()}" name="idPersonne" />

                                        <div class="col s12 add-item-form-header">
                                            <div class="col s3 ajouter-pret-titre">
                                                Ajouter un item :
                                            </div>
                                            <div class=" col s2">
                                                <input placeholder="Nom de l'item" id="nomItem" name="nomItem" type="text" class="validate input-item">
                                            </div>
                                            <div class="col s2">
                                                <input placeholder="Type de l'item" id="typeItem" name="typeItem" type="text" class="validate input-item">
                                            </div>
                                            <div class="col s4">
                                                <input  id="date" name="date" type="date" class="validate input-item input-item-date">
                                            </div>
                                            <div class="col s1 col-add-item">
                                                <button class="btn waves-effect waves-light button-list-item " type="submit" name="action">
                                                    <i class="material-icons right icon-button-list-item">done</i>
                                                </button>
                                            </div>
                                        </div>

                                    </form>

                                </div>
                                <div class="divider"></div>
                                <div class="modif-div">
                                    <div class="row no-margin">
                                        <form class="modif-form"  method="post" action="/">

                                            <input type="hidden" value="updateItem" name="fonction" />
                                            <input type="hidden" value="" name="idItem" />

                                            <div class="col s12 add-item-form-header">
                                                <div class="col s3 ajouter-pret-titre">
                                                    Modifier un item :
                                                </div>
                                                <div class=" col s2">
                                                    <input placeholder="Nom de l'item" name="nomItem" type="text" class="validate input-item">
                                                </div>
                                                <div class="col s2">
                                                    <input placeholder="Type de l'item" name="typeItem" type="text" class="validate input-item">
                                                </div>
                                                <div class="col s4">
                                                    <input   name="date" type="date" class="validate input-item input-item-date">
                                                </div>
                                                <div class="col s1 col-add-item">
                                                    <button class="btn waves-effect waves-light button-list-item " type="submit" name="action">
                                                        <i class="material-icons right icon-button-list-item">done</i>
                                                    </button>
                                                </div>
                                            </div>

                                        </form>

                                    </div>
                                    <div class="divider"></div>
                                </div>

                                <ul class="collection">

                                        <c:forEach items="${personne.getChild(\"items\").getChildren(\"item\")}" var="item">
                                            <li class="collection-item avatar ">
                                                <i class="material-icons circle icon-item-list">folder</i>
                                                <span class="info-personne ">Nom :</span><span class="item-info-nom">${item.getChild("nom").getText()}</span>

                                                <form  method="post" action="/" class="form-list">
                                                    <input type="hidden" value="deleteItem" name="fonction" />
                                                    <input type="hidden" value="${item.getAttribute("id").getValue()}" name="idItem" />
                                                    <button class="btn waves-effect waves-light button-list " type="submit" name="action">
                                                        <i class="material-icons right icon-button-list">clear</i>
                                                    </button>
                                                </form>

                                                <button class="btn waves-effect waves-light button-list modif-item" type="submit" name="action">
                                                    <i class="material-icons right icon-button-list">mode_edit</i>
                                                </button>

                                                <br>
                                                <span class="info-personne ">Type :</span><span class="item-info-type">${item.getChild("type").getText()}</span>
                                                <br>
                                                <span class="info-personne ">Date de prêt:</span><span class="item-info-date">${item.getChild("date").getText()}</span>

                                            </li>
                                        </c:forEach>
                                    </ul>
                            </div>
                        </li>

                    </c:forEach>

                </ul>

            </div>

            <div class="row add-row">
                <div class="col s12  teal lighten-2">
                    <h5 class="white-text">Ajouter une personne</h5>
                </div>
                <div class="col s12 no-padding">

                    <form method="post" action="/">

                        <div class="input-field col s6">
                            <input id="nom" name="nom" type="text" class="validate">
                            <label for="nom">Nom</label>
                        </div>
                        <div class="input-field col s6">
                            <input id="prenom" name="prenom" type="text" class="validate">
                            <label for="prenom">Prenom</label>
                        </div>

                        <div class="input-field col s12 center no-margin add-personne-12">
                            <input type="hidden" value="addPersonne" name="fonction" />
                            <button class="btn waves-effect waves-light" type="submit" name="action">
                                Ajouter
                            </button>
                        </div>

                    </form>

                </div>
            </div>

        </div>

        <script src="<c:url value="/resources/js/jquery.js" />"></script>
        <script src="<c:url value="/resources/js/main.js" />"></script>
        <script src="<c:url value="/resources/js/materialize.js" />"></script>
    </body>
</html>