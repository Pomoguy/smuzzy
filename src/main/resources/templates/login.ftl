<#import "parts/common.ftl" as com>

<@com.page>

    <div id="login">
        <h3 class="text-center text-white pt-5">Login form</h3>
        <div class="container">
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                        <form id="login-form" class="form" action="/login" method="post">
                            <h3 class="text-center text-info">Вход в систему</h3>

                            <div class="form-group">
                                <label for="username" class="text-info">Имя:</label><br>
                                <input type="text" name="username" id="username" class="form-control ${(usernameError??)?string('is-invalid', '')}"
                                       value="<#if user??>${user.username}</#if>"/>
                                <#if usernameError??>
                                    <div class="invalid-feedback">
                                        ${usernameError}
                                    </div>
                                </#if>
                            </div>

                            <div class="form-group">
                                <label for="password" class="text-info">Пароль:</label><br>
                                <input type="password" name="password" id="password" class="form-control ${(passwordError??)?string('is-invalid', '')}"
                                       value="<#if user??>${user.password}</#if>"/>
                                <#if passwordError??>
                                    <div class="invalid-feedback">
                                        ${passwordError}
                                    </div>
                                </#if>
                            </div>

                            <div class="form-group">
                                <label for="remember-me" class="text-info"><span>Remember me</span> <span><input id="remember-me" name="remember-me" type="checkbox"></span></label><br>
                                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                <input type="submit" name="submit" class="btn btn-info btn-md" value="submit">
                            </div>
                            <div id="register-link" class="text-right">
                                <a href="/registration" class="text-info">Регистрация</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</@com.page>