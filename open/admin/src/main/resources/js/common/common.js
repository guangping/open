/*辅助类*/
var ServiceUtils = ServiceUtils || {};
ServiceUtils = {
    getInputDomain: function (divId) {
        var me = this;
        var iv = {};
        //checkbox
        $("#" + divId).find("input[type='checkbox']:checked").each(function () {
            var $obj = $(this);
            var ivId = $obj.attr("name");
            //真值
            var ivVal = $obj.val();
            if (!me.isEmpty(ivVal)) {
                me.setObjVal(iv, ivId, ivVal);
            }
        });

        //text
        $("#" + divId).find("input[type='text']").each(function () {
            var $obj = $(this);
            var ivId = $obj.attr("name");
            var ivVal = $obj.val();
            if (!me.isEmpty(ivVal)) {
                me.setObjVal(iv, ivId, ivVal);
            }
        });
        //password
        $("#" + divId).find("input[type='password']").each(function () {
            var $obj = $(this);
            var ivId = $obj.attr("name");
            var ivVal = $obj.val();
            if (!me.isEmpty(ivVal)) {
                me.setObjVal(iv, ivId, ivVal);
            }
        });

        //hidden
        $("#" + divId).find("input[type='hidden']").each(function () {
            var $obj = $(this);
            var ivId = $obj.attr("name");
            var ivVal = $obj.val();
            if (!me.isEmpty(ivVal)) {
                me.setObjVal(iv, ivId, ivVal);
            }
        });

        //select
        $("#" + divId).find("select").each(function () {
            var $obj = $(this);
            var ivId = $obj.attr("name");
            //真值
            var ivVal = $obj.val();
            if (!me.isEmpty(ivVal)) {
                me.setObjVal(iv, ivId, ivVal);
            }
        });

        //textarea
        $("#" + divId).find("textarea").each(function () {
            var $obj = $(this);
            var ivId = $obj.attr("name");
            //真值
            var ivVal = $obj.val();
            if (!me.isEmpty(ivVal)) {
                me.setObjVal(iv, ivId, ivVal);
            }
        });

        //radio
        $("#" + divId).find("input[type='radio']:checked").each(function () {
            var $obj = $(this);
            var ivId = $obj.attr("name");
            //真值
            var ivVal = $obj.val();
            me.setObjVal(iv, ivId, ivVal);
        });
        return iv;
    },
    setObjVal: function (obj, id, val) {
        var me = this;
        if (me.isEmpty(obj) || me.isEmpty(id)) return;
        if (!obj.hasOwnProperty(id)) {
            obj[id] = val;
        } else {
            obj[id] = obj[id] + "," + val;
        }
    },
    isEmpty: function (obj) {
        var me = this;
        if (null == obj || "undefined" == obj) {
            return true;
        } else {
            var objType = typeof obj;
            if ('string' === objType && me.getByteLen(obj) == 0) {
                return true;
            }
            if ($.isArray(obj) && obj.length == 0) {
                return true;
            }
            return false;
        }
    },
    getByteLen: function (str) {
        var l = str.length;
        var n = l;
        for (var i = 0; i < l; i++) {
            if (str.charCodeAt(i) < 0 || str.charCodeAt(i) > 255) {
                n++;
            }
        }
        return n;
    },
    /**
     * 获取指定form中元素值
     * @param formId 表单id
     * @param elemName 表单中元素名称
     * @returns
     */
    genFormElemValue: function (formId, elemName) {
        if (formId) {
            return jQuery("#" + formId).find("[name=" + elemName + "]").val();
        }
        return null;
    },
    /**
     * 获取URL中参数
     *
     * @param href
     * @returns
     */
    getURLParam: function (href) {
        var retval = {};
        href = decodeURI(href);
        if (href && -1 == href.indexOf("?")) {
            href = "?" + href;
        }
        if (href && href.length > 0) {
            var args = href.split("?");
            // 含有参数
            if (args[0] !== href) {
                var params = args[1];
                args = params.split("&");
                if (args[0].length > 0) {
                    for (var index = 0; index < args.length; index++) {
                        var param = args[index].split("=");
                        if (param.length > 1) {
                            var name = param[0];
                            retval[name] = param[1];
                        }
                    }
                }
            }
        }
        return retval;
    },
    /**
     * 获取cookie值
     * @param name cookie名称
     * @returns
     */
    getCookie: function (name) {
        var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
        if (null != arr) {
            return decodeURIComponent(arr[2]);
        }
        return null;
    }, /**
     * 设置cookie
     * @param cookieName cookie名称
     * @param cookieValue cookie值
     * @param cookieExpires cookie有效期（单位：秒）
     * @param cookiePath cookie路径
     * @returns
     */
    setCookie: function (cookieName, cookieValue, expires) {
        var options = {};
        options.expires = expires;
        cookieValue = escape(cookieValue);//编码latin-1
        $.cookie(cookieName, cookieValue, options);
    }, /**
     * 获取字符串长度（中文字符长度为2）
     * @param str
     * @returns
     */
    strLen: function (str) {
        if (str) {
            var cArr = str.match(/[^\x00-\xff]/ig);
            return str.length + (null == cArr ? 0 : cArr.length);
        }
        return 0;
    },
    /**
     * 获取子串（每个中文字符长度算2位）
     * @param sourceStr
     * @param len
     * @returns {String}
     */
    subStr: function (sourceStr, len) {
        var subStr = '';
        var currLen = 0;
        if (sourceStr) {
            if (len < 1) {
                return '';
            }
            var strLen = sourceStr.length;
            for (var i = 0; i < strLen; i++) {
                var tmpStr = sourceStr[i];
                var cArr = tmpStr.match(/[^\x00-\xff]/ig);
                currLen += (null == cArr ? 1 : 2);
                if (currLen >= len || (i == strLen - 1)) {
                    subStr = sourceStr.substring(0, i + 1);
                    break;
                }
            }
        }
        return subStr;
    },
    //去掉字符串头尾空格
    trimStr: function (str) {
        return str.replace(/(^\s*)|(\s*$)/g, "");
    },
    //去掉字符串中所有的空格
    trimAllStr: function (str) {
        return str.replace(/\s/g, '');
    },
    /**
     添加收藏夹
     *@param sURL URL
     *@param sTitle 标题
     */
    addBookmark: function (sURL, sTitle) {
        try {
            window.external.addFavorite(sURL, sTitle);
        } catch (e) {
            try {
                window.sidebar.addPanel(sTitle, sURL, "");
            } catch (e) {
            }
        }
    },//判断邮箱
    checkEmail: function (str) {
        var patrn = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        if (!patrn.test(str)) return false;
        else return true;
    },
    isNumber: function (val) {
        var reg = /^[\d|\.|,]+$/;
        return reg.test(val);
    },
    isTime: function (val) {
        var reg = /^\d{4}-\d{2}-\d{2}\s\d{2}:\d{2}$/;
        return reg.test(val);
    },
    x: function () {//当前鼠标X坐标
        return Browser.isIE ? event.x + document.documentElement.scrollLeft - 2 : e.pageX;
    },
    y: function () {//当前鼠标Y坐标
        return Browser.isIE ? event.y + document.documentElement.scrollTop - 2 : e.pageY;
    },
    formatDate: function (date, format) {
        if (!date) return;
        if (!format) format = "yyyy-MM-dd";
        switch (typeof date) {
            case "string":
                date = new Date(date.replace(/-/, "/"));
                break;
            case "number":
                date = new Date(date);
                break;
        }
        if (!date instanceof Date) return;
        var dict = {
            "yyyy": date.getFullYear(),
            "M": date.getMonth() + 1,
            "d": date.getDate(),
            "H": date.getHours(),
            "m": date.getMinutes(),
            "s": date.getSeconds(),
            "MM": ("" + (date.getMonth() + 101)).substr(1),
            "dd": ("" + (date.getDate() + 100)).substr(1),
            "HH": ("" + (date.getHours() + 100)).substr(1),
            "mm": ("" + (date.getMinutes() + 100)).substr(1),
            "ss": ("" + (date.getSeconds() + 100)).substr(1)
        };
        return format.replace(/(yyyy|MM?|dd?|HH?|ss?|mm?)/g, function () {
            return dict[arguments[0]];
        });
    },
    isMobile: function (val) {
        var patt = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9]|17[0-9])\d{8}$/;
        return patt.test(val);
    },
    isTel: function (val) {
        var reg = /^0\d{2,3}-?\d{7,8}$/;
        return reg.test(val);
    },
    formatDateDefault: function (obj) {
        return new Date(obj).pattern('yyyy-MM-dd HH:mm:ss');
    },
    formatDate: function (obj) {
        return new Date(obj).pattern('yyyy-mm-dd');
    },
    formatDateTime: function (obj) {
        return new Date(obj).pattern('yyyyMMddHHmmss');
    },
    getDate: function (days) {
        var now = new Date();
        if (days >= 1) {
            now = new Date(now.getTime() + 86400000 * days);
        }
        var yyyy = now.getFullYear(), mm = (now.getMonth() + 1).toString(), dd = now.getDate().toString();
        if (mm.length == 1) {
            mm = '0' + mm;
        }
        if (dd.length == 1) {
            dd = '0' + dd;
        }
        return (yyyy + '-' + mm + '-' + dd);
    },
    getHours: function (hour) {
        var c = new Date();
       // c.setHours(c.getHours() + hour);
        c.setTime(c.getTime()+hour*3600*1000+10*60*1000);
        return c;
    }
}

Date.prototype.pattern = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    var week = {
        "0": "/u65e5",
        "1": "/u4e00",
        "2": "/u4e8c",
        "3": "/u4e09",
        "4": "/u56db",
        "5": "/u4e94",
        "6": "/u516d"
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    if (/(E+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f" : "/u5468") : "") + week[this.getDay() + ""]);
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
}




