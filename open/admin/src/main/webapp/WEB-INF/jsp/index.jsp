<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js"> <!--<![endif]-->
<head>
    <meta charset="utf-8"/>
    <title>Admin Dashboard Template</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <meta name="MobileOptimized" content="320">
    <link href="${ctx}/resources/js/metronic/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="${ctx}/resources/js/metronic/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/resources/js/metronic/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/resources/js/metronic/css/style-metronic.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/resources/js/metronic/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/resources/js/metronic/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/resources/js/metronic/css/plugins.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/resources/js/metronic/css/pages/tasks.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/resources/js/metronic/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="${ctx}/resources/js/metronic/css/custom.css" rel="stylesheet" type="text/css"/>

    <link rel="shortcut icon" href="favicon.ico"/>
</head>

<body class="page-header-fixed">

<div class="header navbar navbar-inverse navbar-fixed-top">
    <div class="header-inner">

        <a class="navbar-brand" href="index.html">
            <img src="assets/img/logo.png" alt="logo" class="img-responsive"/>
        </a>

        <a href="javascript:;" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <img src="${ctx}/resources/js/metronic/img/menu-toggler.png" alt=""/>
        </a>

        <ul class="nav navbar-nav pull-right">

            <li class="dropdown user">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
                   data-close-others="true">
                    <img alt="" src="assets/img/avatar1_small.jpg"/>
                    <span class="username">Bob Nilson</span>
                    <i class="icon-angle-down"></i>
                </a>
                <ul class="dropdown-menu">
                    <%-- <li><a href="extra_profile.html"><i class="icon-user"></i> My Profile</a>
                     </li>
                     <li><a href="page_calendar.html"><i class="icon-calendar"></i> My Calendar</a>
                     </li>
                     <li><a href="inbox.html"><i class="icon-envelope"></i> My Inbox <span
                             class="badge badge-danger">3</span></a>
                     </li>
                     <li><a href="#"><i class="icon-tasks"></i> My Tasks <span class="badge badge-success">7</span></a>
                     </li>
                     <li class="divider"></li>
                     <li><a href="javascript:;" id="trigger_fullscreen"><i class="icon-move"></i> Full Screen</a>
                     </li>
                     <li><a href="extra_lock.html"><i class="icon-lock"></i> Lock Screen</a>
                     </li>--%>
                    <li><a href="${ctx}/logOut.action"><i class="icon-key"></i>退出</a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</div>

<div class="clearfix"></div>
<div class="page-container">
    <div class="page-sidebar navbar-collapse collapse">
        <ul class="page-sidebar-menu">
            <li>
                <div class="sidebar-toggler hidden-phone"></div>
            </li>
            <li class="start active ">
                <a href="index.html">
                    <i class="icon-home"></i>
                    <span class="title">首页</span>
                    <span class="selected"></span>
                </a>
            </li>
            <li class="tooltips" data-placement="left"
                data-original-title="Frontend&nbsp;Theme For&nbsp;Metronic&nbsp;Admin">
                <a href="http://keenthemes.com/preview/index.php?theme=metronic_frontend" target="_blank">
                    <i class="icon-gift"></i>
                    <span class="title">Frontend Theme</span>
                </a>
            </li>
            <li class="">
                <a href="javascript:;">
                    <i class="icon-bookmark-empty"></i>
                    <span class="title">UI Features</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a class="ajaxify" href="${ctx}/test1.action">
                            General</a>
                    </li>
                    <li>
                        <a class="ajaxify" href="${ctx}/test1.action">
                            Buttons</a>
                    </li>
                </ul>
            </li>

            <li>
                <a class="active" href="javascript:;">
                    <i class="icon-leaf"></i>
                    <span class="title">3 Level Menu</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a href="javascript:;">
                            Item 1
                            <span class="arrow"></span>
                        </a>
                        <ul class="sub-menu">
                            <li><a class="ajaxify" href="ut.html">测试</a></li>
                            <li><a href="#">Sample Link 2</a></li>
                            <li><a href="#">Sample Link 3</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="javascript:;">
                            Item 1
                            <span class="arrow"></span>
                        </a>
                        <ul class="sub-menu">
                            <li><a href="#">Sample Link 1</a></li>
                            <li><a href="#">Sample Link 1</a></li>
                            <li><a href="#">Sample Link 1</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#">
                            Item 3
                        </a>
                    </li>
                </ul>
            </li>
            <li class="last ">
                <a href="charts.html">
                    <i class="icon-bar-chart"></i>
                    <span class="title">Visual Charts</span>
                </a>
            </li>
        </ul>
    </div>
    <div class="modal fade" id="ajax" tabindex="-1" role="basic" aria-hidden="true">
        <img src="${ctx}/resources/js/metronic/img/ajax-modal-loading.gif" alt="" class="loading">
    </div>
    <div class="page-content">
        <div class="page-content-body">
            <h1>管理平台</h1>
        </div>
    </div>
</div>

<jsp:include page="./common/footer.jsp"/>

<!--[if lt IE 9]>
<script src="${ctx}/resources/js/metronic/plugins/respond.min.js"></script>
<script src="${ctx}/resources/js/metronic/plugins/excanvas.min.js"></script>
<![endif]-->
<script src="${ctx}/resources/js/jquery/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/metronic/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/metronic/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js"
        type="text/javascript"></script>
<script src="${ctx}/resources/js/metronic/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/metronic/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js"
        type="text/javascript"></script>
<script src="${ctx}/resources/js/metronic/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
        type="text/javascript"></script>
<script src="${ctx}/resources/js/metronic/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/metronic/plugins/jquery.cookie.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/metronic/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/metronic/scripts/app.js" type="text/javascript"></script>
<script type="text/javascript">
    jQuery(document).ready(function () {
        App.init();

        $('.page-sidebar .ajaxify.start').click();

        $('.ajaxifyLoad').on('click', function (e) {
            e.preventDefault();
            var url = $(this).attr("href");
            loadHtml(url);
        })

        function loadAjaxHtml(obj) {
            var url = $(obj).attr("href");
            loadHtml(url);
        }

        function loadHtml(url) {
            App.scrollTop();
            var pageContent = $('.page-content');
            var pageContentBody = $('.page-content .page-content-body');

            App.blockUI(pageContent, false);
            $.ajax({
                type: "GET",
                cache: false,
                url: url,
                dataType: "html",
                success: function (res) {
                    App.unblockUI(pageContent);
                    pageContentBody.html(res);
                    App.fixContentHeight();
                    App.initAjax();
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    pageContentBody.html('<h4>404!</h4>');
                    App.unblockUI(pageContent);
                },
                async: false
            });
        }
    });
</script>
</body>
</html>
