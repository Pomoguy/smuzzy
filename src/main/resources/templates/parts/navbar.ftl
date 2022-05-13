<#import "common.ftl" as com>
<#include "security.ftl">

<#macro nav>
    <@com.page>
        <nav class="navbar navbar-expand-lg navbar-dark" style="background-color:#960b10;">
            <a class="navbar-brand" href="/main">Smuzzy</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="/main">Главная</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="/sheduled">События</a>
                    </li>
                    <li class="nav-item dropdown">
                                            <a class="nav-link dropdown-toggle" href="#" id="demo" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                Настройки
                                            </a>
                                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                                <a class="dropdown-item" href="/home">Общие</a>
                                                <a class="dropdown-item" href="/user">Пользователи</a>
                                            </div>
                    </li>


                </ul>

                <div class="navbar-nav">
                    <#if known>
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Здравствуйте, ${name}
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <li>
                                <form class="dropdown-item" action="/logout" method="post">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                    <div><input class="dropdown-item" type="submit" value="Logout"/></div>
                                </form>
                            </li>
                        </ul>
                        <#else>
                            <li class="nav-item">
                                <a class="nav-link" href="/login">Вход</a>
                            </li>
                    </#if>
                </div>
            </div>
        </nav>
        <div class="container mt-3">
            <#nested>
        </div>
    </@com.page>
</#macro>