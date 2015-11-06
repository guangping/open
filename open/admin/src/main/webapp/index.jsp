<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8"/>
    <title>Metronic | Admin Dashboard Template</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <meta name="MobileOptimized" content="320">
    <link href="${ctx}/resources/js/metronic/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="${ctx}/resources/js/metronic/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/resources/js/metronic/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/resources/js/metronic/plugins/select2/select2_metro.css"/>
    <link href="${ctx}/resources/js/metronic/css/style-metronic.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/resources/js/metronic/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/resources/js/metronic/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/resources/js/metronic/css/plugins.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/resources/js/metronic/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="${ctx}/resources/js/metronic/css/pages/login.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/resources/js/metronic/css/custom.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="favicon.ico"/>
</head>
<body class="login">
<div class="logo">
    <img src="assets/img/logo-big.png" alt=""/>
</div>
<div class="content">
    <form class="login-form" action="index.html" method="post">
        <h3 class="form-title">管理平台</h3>

        <div class="alert alert-error hide">
            <button class="close" data-dismiss="alert"></button>
            <span>Enter any username and password.</span>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">用户名</label>

            <div class="input-icon">
                <i class="icon-user"></i>
                <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="用户名"
                       name="username"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">密码</label>

            <div class="input-icon">
                <i class="icon-lock"></i>
                <input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="密码"
                       name="password"/>
            </div>
        </div>
        <div class="form-actions">
            <label class="checkbox">
                <input type="checkbox" name="remember" value="1"/> Remember me
            </label>
            <button type="submit" class="btn green pull-right">
                登录 <i class="m-icon-swapright m-icon-white"></i>
            </button>
        </div>
    </form>
</div>
<div class="copyright">
    2013 &copy; Metronic. Admin Dashboard Template.
</div>
<!--[if lt IE 9]>
<script src="${ctx}/resources/js/metronic/plugins/respond.min.js"></script>
<script src="${ctx}/resources/js/metronic/plugins/excanvas.min.js"></script>
<![endif]-->
<script src="${ctx}/resources/js/jquery/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/metronic/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/metronic/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/metronic/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js"
        type="text/javascript"></script>
<script src="${ctx}/resources/js/metronic/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/metronic/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/metronic/plugins/jquery.cookie.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/metronic/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>

<script src="${ctx}/resources/js/metronic/plugins/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/resources/js/metronic/plugins/select2/select2.min.js"></script>

<script src="${ctx}/resources/js/login/login.js" type="text/javascript"></script>
<script>
    jQuery(document).ready(function () {
        App.init();
        Login.init();
    });
</script>
</body>
</html>
