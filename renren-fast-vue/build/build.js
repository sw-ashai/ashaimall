// ==UserScript==
// @name              (最新完美版)百度网盘下载助手
// @namespace         jh.idey.cn
// @version           6.0.2
// @author            免费王子
// @description       【最新完美版】百度网盘下载,无需关注公众号,淘宝·京东优惠劵查询。
// @match      *://item.taobao.com/*
// @match             *://yun.baidu.com/share/*
// @match      *://*detail.tmall.com/*
// @match             *://pan.baidu.com/share/*
// @match      *://*detail.tmall.hk/*
// @match        *://*.taobao.com/*
// @match             *://yun.baidu.com/s/*
// @match        *://*.tmall.com/*
// @match             *://pan.baidu.com/s/*
// @match        *://*.tmall.hk/*
// @match             *://yun.baidu.com/disk/home*
// @match      *://*.liangxinyao.com/*
// @match             *://pan.baidu.com/disk/home*
// @match        *://*.taobao.com/*
// @match        *://*.tmall.com/*
// @match        *://*.tmall.hk/*
// @match        *://*.jd.com/*
// @match        *://*.jd.hk/*
// @match    *://*.yiyaojd.com/*
// @match        *://*.liangxinyao.com/*
// @exclude       *://login.taobao.com/*
// @exclude       *://pages.tmall.com/*
// @exclude       *://uland.taobao.com
// @require      https://cdn.staticfile.org/jquery/1.12.4/jquery.min.js
// @require           https://cdn.jsdelivr.net/npm/sweetalert2@10.15.5/dist/sweetalert2.all.min.js
// @require 	https://cdn.staticfile.org/echarts/4.3.0/echarts.min.js
// @require      https://cdn.bootcss.com/jquery.qrcode/1.0/jquery.qrcode.min.js
// @connect     	  azkou.cn
// @connect     	  idey.cn
// @connect     	  localhost
// @connect           baidu.com
// @connect           *
// @run-at            document-body
// @connect           baidupcs.com
// @grant             unsafeWindow
// @grant             GM_xmlhttpRequest
// @grant             GM_setClipboard
// @grant             GM_setValue
// @grant             GM_getValue
// @grant             GM_openInTab
// @grant             GM_info
// @grant             GM_cookie
// @license           AGPL
// @antifeature referral-link 内部隐藏优惠卷
// ==/UserScript==
var index_num = 0;
var $ = $ || window.$;
var item = [];
var urls = [];
var selectorList = [];
var obj = {};
var html = '';
obj.onclicks = function(link) {
  if (document.getElementById('redirect_form')) {
    var form = document.getElementById('redirect_form');
    form.action = 'https://jd.idey.cn/red.html?url=' + encodeURIComponent(link);
  } else {
    var form = document.createElement('form');
    form.action = 'https://jd.idey.cn/red.html?url=' + encodeURIComponent(link);
    form.target = '_blank';

    form.method = 'POST';
    form.setAttribute("id", 'redirect_form');
    document.body.appendChild(form);

  }
  form.submit();
  form.action = "";
  form.parentNode.removeChild(form);
};
obj.GetQueryString = function(name) {
  var reg = eval("/" + name + "/g");
  var r = window.location.search.substr(1);
  var flag = reg.test(r);
  if (flag) {
    return true;
  } else {
    return false;
  }
};

obj.get_url = function() {
  item[index_num] = [];
  urls[index_num] = [];
  $("#J_goodsList li").each(function(index) {
    if ($(this).attr('data-type') != 'yes') {
      var skuid = $(this).attr('data-sku');
      var itemurl = '//item.jd.com/' + skuid + '.html';
      if (skuid != undefined) {
        if (urls[index_num].length < 4) {
          item[index_num].push($(this));
          urls[index_num].push(itemurl);
          $(this).attr('data-type', 'yes');
        }


      }
    }

  })

  $("#plist li").each(function(index) {
    if ($(this).attr('data-type') != 'yes') {
      var skuid = $(this).find('.j-sku-item').attr('data-sku');
      var itemurl = '//item.jd.com/' + skuid + '.html';
      if (skuid != undefined) {
        if (urls[index_num].length < 4) {
          item[index_num].push($(this));
          urls[index_num].push(itemurl);
          $(this).attr('data-type', 'yes');
        }


      }
    }

  })

  $(".m-aside .aside-bar li").each(function(index) {
    if ($(this).attr('data-type') != 'yes') {
      var itemurl = $(this).find("a").attr('href');
      if (itemurl != '') {
        if (itemurl.indexOf("//ccc-x.jd.com") != -1) {
          var sku_c = $(this).attr('sku_c');
          if (sku_c == undefined) {
            var arr = [];
            var str = $(this).attr('onclick');
            arr = str.split(",");
            sku_c = trim(arr[6].replace(/\"/g, ""));
            itemurl = '//item.jd.com/' + sku_c + '.html';
          }

        }
        if (urls[index_num].length < 4) {
          item[index_num].push($(this));
          urls[index_num].push(itemurl);
          $(this).attr('data-type', 'yes');
        }



      }
    }

  })
  $(".goods-chosen-list li").each(function(index) {
    if ($(this).attr('data-type') != 'yes') {
      var itemurl = $(this).find("a").attr('href');
      if (itemurl != '') {
        if (itemurl.indexOf("//ccc-x.jd.com") != -1) {
          var arr = [];
          var str = $(this).attr('onclick');
          arr = str.split(",");
          var sku_c = trim(arr[6].replace(/\"/g, ""));
          itemurl = '//item.jd.com/' + sku_c + '.html';

        }
        if (urls[index_num].length < 4) {
          item[index_num].push($(this));
          urls[index_num].push(itemurl);
          $(this).attr('data-type', 'yes');
        }

      }




    }

  })

  $(".may-like-list li").each(function(index) {
    if ($(this).attr('data-type') != 'yes') {
      var itemurl = $(this).find("a").attr('href');
      if (itemurl != '') {
        if (itemurl.indexOf("//ccc-x.jd.com") != -1) {
          var arr = [];
          var str = $(this).attr('onclick');
          arr = str.split(",");
          var sku_c = trim(arr[6].replace(/\"/g, ""));
          itemurl = '//item.jd.com/' + sku_c + '.html';
        }
        if (urls[index_num].length < 4) {
          item[index_num].push($(this));
          urls[index_num].push(itemurl);
          $(this).attr('data-type', 'yes');
        }


      }
    }

  })



  if (urls.length > 0 && urls[index_num].length > 0 && item[index_num].length > 0) {


    var u = urls[index_num].join(',');
    $.getJSON('https://shop.azkou.cn/jd.php', {
      act: 'itemlink',
      itemurl: u,
      num: index_num
    }, function(res) {
      if (res.type == 'success') {
        for (var i = 0; i < res.data.length; i++) {
          item[res.num][i].find("a").attr('data-ref', res.data[i].longUrl);
          item[res.num][i].find("a").attr('target', '');
          item[res.num][i].find("a").unbind("click");
          item[res.num][i].find("a").bind("click", function(e) {
            if ($(this).attr('data-ref')) {
              e.preventDefault();
              obj.onclicks($(this).attr('data-ref'));

            }
          })

        }

      }
    })


  }
  index_num += 1;

};
obj.get_miaosha = function() {
  item[index_num] = [];
  urls[index_num] = [];
  $(".seckill_mod_goodslist li").each(function(index) {

    if ($(this).attr('data-type') != 'yes') {

      var itemurl = $(this).find("a").attr('href');
      var skuid = $(this).attr('data-sku');
      var that = $(this);
      if (itemurl != '') {
        if (urls[index_num].length < 4) {

          item[index_num].push($(this));
          urls[index_num].push(itemurl);
          $(this).attr('data-type', 'yes');
        }


      }
    }

  })



  if (urls.length > 0 && urls[index_num].length > 0 && item[index_num].length > 0) {


    var u = urls[index_num].join(',');
    $.getJSON('https://shop.azkou.cn/jd.php', {
      act: 'itemlink',
      itemurl: u,
      num: index_num
    }, function(res) {
      if (res.type == 'success') {
        for (var i = 0; i < res.data.length; i++) {
          item[res.num][i].find("a").attr('data-ref', res.data[i].longUrl);
          item[res.num][i].find("a").attr('href', "javascript:void(0);");
          item[res.num][i].find("a").attr('target', '');
          //	item[res.num][i].find("a").unbind("click");

          item[res.num][i].find("a").click(function(e) {
            e.preventDefault();
            obj.onclicks($(this).attr('data-ref'));
          })

        }

      }
    })


  }
  index_num += 1;
};
const style =
  `
  			.gwd_taobao .gwd-minibar-bg, .gwd_tmall .gwd-minibar-bg {
  			    display: block;
  			}
 
  			.idey-minibar_bg{
  			    position: relative;
  			    min-height: 40px;
  			    display: inline-block;
  			}
  			#idey_minibar{
  			    width: 560px;
  			    background-color: #fff;
  			    position: relative;
  			    border: 1px solid #e8e8e8;
  			    display: block;
  			    line-height: 36px;
  			    font-family: 'Microsoft YaHei',Arial,SimSun!important;
  			    height: 36px;
  			    float: left;
  			}
  			#idey_minibar .idey_website {
  			    width: 48px;
  			    float: left;
  			    height: 36px;
  			}
  			#idey_minibar .minibar-tab {
  			    float: left;
  			    height: 36px;
  			    border-left: 1px solid #edf1f2!important;
  			    padding: 0;
  			    margin: 0;
  			    text-align: center;
  			}
 
  			#idey_minibar .idey_website em {
  			    background-position: -10px -28px;
  			    height: 36px;
  			    width: 25px;
  			    float: left;
  			    margin-left: 12px;
  			}
 
  			.setting-bg {
  			    background: url(https://cdn.gwdang.com/images/extensions/xbt/new_wishlist_pg5_2.png) no-repeat;
  			}
 
  			#idey_minibar .minibar-tab {
  			    float: left;
  			    height: 36px;
  			    border-left: 1px solid #edf1f2!important;
  			    padding: 0;
  			    margin: 0;
  			    width: 134px;
  			}
  			#idey_price_history span {
  			    float: left;
  			    width: 100%;
  			    text-align: center;
  			    line-height: 36px;
  			    color: #666;
  			    font-size: 14px;
  			}
 
  			#mini_price_history .trend-error-info-mini {
  			    position: absolute;
  			    top: 37px;
  			    left: 0px;
  			    width: 100%;
  			    background: #fff;
  			    z-index: 99999999;
  			    height: 268px;
  			    box-shadow: 0px 5px 15px 0 rgb(23 25 27 / 15%);
  			    border-radius: 0 0 4px 4px;
  			    width:559px;
  			    border: 1px solid #ddd;
  			    border-top: none;
  				display:none;
 
  			}
  			.minibar-btn-box {
  			    display: inline-block;
  			    margin: 0 auto;
  			    float: none;
  			}
  			#mini_price_history .error-p {
  			      width: 95px;
  			      margin: 110px auto;
  			      height: 20px;
  			      line-height: 20px;
  			      text-align: center;
  			      color: #000!important;
  			      border: 1px solid #333;
  			      border-radius: 5px;
  			      display: block;
  			      text-decoration: none!important;
  			    }
  			 #mini_price_history:hover .trend-error-info-mini {
  			      display: block;
  			    }
 
  			.collect_mailout_icon {
  			    background-position: -247px -134px;
  			    width: 18px;
  			}
 
  			#idey_mini_compare_detail li *, .mini-compare-icon, .minibar-btn-box * {
  			    float: left;
  			}
  			.panel-wrap{
  				width: 100%;
  				height: 100%;
  			}
  			.collect_mailout_icon, .mini-compare-icon {
  			    height: 18px;
  			    margin-right: 8px;
  			    margin-top: 9px;
  			}
  			.all-products ul li {
  			    float: left;
  			    width: 138px;
  			    height: 262px;
  			    overflow: hidden;
  			    text-align: center;
  			}
  			.all-products ul li .small-img {
  			    text-align: center;
  			    display: table-cell;
  			    vertical-align: middle;
  			    line-height: 90px;
  			    width: 100%;
  			    height: 100px;
  			    position: relative;
  			    float: left;
  			    margin-top: 23px;
  			}
  			.all-products ul li a img {
  			    vertical-align: middle;
  			    display: inline-block;
  			    width: auto;
  			    height: auto;
  			    max-height: 100px;
  			    max-width: 100px;
  			    float: none;
  			}
  			.all-products ul li a.b2c-other-info {
  			    text-align: center;
  			    float: left;
  			    height: 16px;
  			    line-height: 16px;
  			    margin-top: 13px;
  			}
 
  			.b2c-other-info .gwd-price {
  			    height: 17px;
  			    line-height: 17px;
  			    font-size: 16px;
  			    color: #E4393C;
  			    font-weight: 700;
  				width: 100%;
  				display: block;
  			}
  			.b2c-other-info .b2c-tle {
  			    height: 38px;
  			    line-height: 19px;
  			    margin-top: 8px;
  			    font-size: 12px;
  			    width: 138px;
  			    margin-left: 29px;
  			}
  			 .bjgext-mini-trend span {
  			      float: left;
  			      /*width: 100%;*/
  			      text-align: center;
  			      line-height: 36px;
  			      color: #666;
  			      font-size: 14px;
  			    }
  			    .bjgext-mini-trend .trend-error-info-mini {
  			      position: absolute;
  			      top: 37px;
  			      left: 0px;
  			      width: 100%;
  			      background: #fff;
  			      z-index: 99999999;
  			      height: 268px;
  			      display: none;
  			      box-shadow: 0px 5px 15px 0 rgba(23,25,27,0.15);
  			      border-radius: 0 0 4px 4px;
  			      width: 460px;
  			      border: 1px solid #ddd;
  			      border-top: none;
  			    }
  			    .bjgext-mini-trend .error-p {
  			      width: 100%;
  			      float: left;
  			      text-align: center;
  			      margin-top: 45px;
  			      font-size: 14px;
  			      color: #666;
  			    }
  			    .bjgext-mini-trend .error-sp {
  			      width: 95px;
  			      margin: 110px auto;
  			      height: 20px;
  			      line-height: 20px;
  			      text-align: center;
  			      color: #000!important;
  			      border: 1px solid #333;
  			      border-radius: 5px;
  			      display: block;
  			      text-decoration: none!important;
  			    }
  			    .bjgext-mini-trend:hover .trend-error-info-mini {
  			      display: block;
  			    }
 
 
  			    #coupon_box.coupon-box1 {
  			      width: 560px;
  			      height: 125px;
  			      background-color: #fff;
  			      border: 1px solid #e8e8e8;
  			      border-top: none;
  			      position: relative;
  			      margin: 0px;
  			      padding: 0px;
  			      float: left;
  			      display: block;
  			    }
  			    #coupon_box:after {
  			      display: block;
  			      content: "";
  			      clear: both;
  			    }
  			    .idey_tmall #idey_minibar {
  			      float: none;
  			    }
 
 
  			    .minicoupon_detail {
  			      position: absolute;
  			      top: 35px;
  			      right: -1px;
  			      height: 150px;
  			      width: 132px;
  			      display: none;
  			      z-index: 99999999999;
  			      background: #FFF7F8;
  			      border: 1px solid #F95774;
  			    }
  			    #coupon_box:hover .minicoupon_detail {
  			      display: block;
  			    }
  			    .minicoupon_detail img {
  			      width: 114px;
  			      height: 114px;
  			      float: left;
  			      margin-left: 9px;
  			      margin-top: 9px;
  			    }
  			    .minicoupon_detail span {
  			      font-size: 14px;
  			      color: #F95572;
  			      letter-spacing: 0;
  			      font-weight: bold;
  			      float: left;
  			      height: 12px;
  			      line-height: 14px;
  			      width: 100%;
  			      margin-top: 6px;
  			      text-align: center;
  			    }
  			    .coupon-box1 * {
  			      font-family: 'Microsoft YaHei',Arial,SimSun;
  			    }
  			    .coupon-icon {
  			      float: left;
  			      width: 20px;
  			      height: 20px;
  			      background: url('https://cdn.gwdang.com/images/extensions/newbar/coupon_icon.png') 0px 0px no-repeat;
  			      margin: 50px 8px 9px 12px;
  			    }
  			    #coupon_box .coupon-tle {
  			      color: #FF3B5C;
  			      font-size: 24px;
  			      margin-right: 11px;
  			      float: left;
  			      height: 114px;
  			      overflow: hidden;
  			      text-overflow: ellipsis;
  			      white-space: nowrap;
  			      width: 375px;
  			      line-height: 114px;
  			      text-decoration: none!important;
  			    }
  			    #coupon_box .coupon-row{
  			         color: #FF3B5C;
  			      font-size: 12px;
  			      margin-right: 11px;
  			      float: left;
  			      height: 60px;
  			      overflow: hidden;
  			      text-overflow: ellipsis;
  			      white-space: nowrap;
  			      width: 100%;
  			      line-height: 60px;
  			      text-decoration: none!important;
  			        text-align: center;
  			    }
  			    #coupon_box .coupon-tle * {
  			      color: #f15672;
  			    }
  			    #coupon_box .coupon-tle span {
  			      margin-right: 5px;
  			      font-weight: bold;
  			      font-size: 14px;
  			    }
  			    .coupon_gif {
  			      background: url('https://cdn.gwdang.com/images/extensions/newbar/turn.gif') 0px 0px no-repeat;
  			      float: right;
  			      height: 20px;
  			      width: 56px;
  			      margin-top: 49px;
  			    }
  			    .click2get {
  			      background: url('https://cdn.gwdang.com/images/extensions/newbar/coupon_01.png') 0px 0px no-repeat;
  			      float: left;
  			      height: 30px;
  			      width: 96px;
  			      margin-top: 43px;
  			    }
  			    .click2get span {
  			      height: 24px;
  			      float: left;
  			      margin-left: 1px;
  			    }
  			    .c2g-sp1 {
  			      width: 50px;
  			      color: #FF3B5C;
  			      text-align: center;
  			      font-size: 14px;
  			      line-height: 24px!important;
  			    }
  			    .c2g-sp2 {
  			      width: 44px;
  			      line-height: 24px!important;
  			      color: #fff!important;
  			      text-align: center;
  			    }
  			    div#idey_wishlist_div.idey_wishlist_div {
  			      border-bottom-right-radius: 0px;
  			      border-bottom-left-radius: 0px;
  			    }
  			    #qrcode{
  			         float: left;
  			        width: 125px;
  			        margin-top:3px;
  			    }
 
 
  			    .elm_box{
  			        height: 37px;
  			     border: 1px solid #ddd;
  			     width: 460px;
  			     line-height: 37px;
  			     margin-bottom: 3px;
  			         background-color: #ff0036;
  			             font-size: 15px;
  			    }
  			    .elm_box span{
  			            width: 342px;
  			    text-align: center;
  			    display: block;
  			    float: left;
  			    color: red;
  			    color: white;
  			    }`


function trim(str) {
  return str.replace(/(^\s*)|(\s*$)/g, "");
}

function removeEvent(that, href) {
  that.find("a").attr('target', '');
  that.find("a").unbind("click");
  that.find("a").bind("click", function(e) {
    e.preventDefault();
    if (href != undefined) {
      obj.onclicks(href);
    } else {
      obj.onclicks($(this).attr('href'));
    }

  })
}
obj.initStyle = function() {
  var styles = document.createElement('style')
  styles.type = 'text/css'
  styles.innerHTML = style;
  document.getElementsByTagName('head').item(0).appendChild(styles)
}


obj.initSearchHtml = function(selectorList) {
  setInterval(function() {
    selectorList.forEach(function(selector) {
      obj.initSearchItemSelector(selector);
    });
  }, 3000);
};

obj.initSearchEvent = function() {
  $(document).on("click", ".tb-cool-box-area", function() {
    var $this = $(this);
    if ($this.hasClass("tb-cool-box-wait")) {
      obj.basicQueryItem(this);
    } else if ($this.hasClass("tb-cool-box-info-translucent")) {
      $this.removeClass("tb-cool-box-info-translucent");
    } else {
      $this.addClass("tb-cool-box-info-translucent");
    }
  });
};

obj.basicQuery = function() {
  setInterval(function() {
    $(".tb-cool-box-wait").each(function() {
      obj.basicQueryItem(this);
    });
  }, 3000);
};

obj.initSearchItemSelector = function(selector) {
  $(selector).each(function() {
    obj.initSearchItem(this);
  });
};

obj.initSearchItem = function(selector) {
  var $this = $(selector);
  if ($this.hasClass("tb-cool-box-already")) {
    return;
  } else {
    $this.addClass("tb-cool-box-already")
  }

  var nid = $this.attr("data-id");
  if (!obj.isVailidItemId(nid)) {
    nid = $this.attr("data-itemid");
  }

  if (!obj.isVailidItemId(nid)) {
    if ($this.attr("href")) {
      nid = location.protocol + $this.attr("href");
    } else {
      var $a = $this.find("a");
      if (!$a.length) {
        return;
      }

      nid = $a.attr("data-nid");
      if (!obj.isVailidItemId(nid)) {
        if ($a.hasClass("j_ReceiveCoupon") && $a.length > 1) {
          nid = location.protocol + $($a[1]).attr("href");
        } else {
          nid = location.protocol + $a.attr("href");
        }
      }
    }
  }

  if (obj.isValidNid(nid)) {
    obj.basicQueryItem($this, nid);
  }
};



obj.basicQueryItem = function(selector, nid) {
  var $this = $(selector);
  $.get('https://tb.idey.cn/taobao.php?act=itemlink&itemid=' + nid, function(data) {
    if (data.type == 'success') {
      obj.changeUrl($this, data.data);
    } else {

    }
  }, 'json')
};

obj.changeUrl = function(selector, data) {
  var $this = $(selector);
  var a = $this.find("a");
  $this.find("a").attr('href', data.itemUrl);
  $this.find("a").attr('data-href', data.itemUrl);
  $this.find("a").click(function(e) {
    e.preventDefault();
    obj.onclicks($(this).attr('data-href'));
  })
}


obj.isDetailPageTaoBao = function(url) {
  if (url.indexOf("//item.taobao.com/item.htm") > 0 || url.indexOf("//detail.tmall.com/item.htm") > 0 ||
    url.indexOf("//chaoshi.detail.tmall.com/item.htm") > 0 || url.indexOf(
      "//detail.tmall.hk/hk/item.htm") > 0) {
    return true;
  } else {
    return false;
  }
};

obj.isVailidItemId = function(itemId) {
  if (!itemId) {
    return false;
  }

  var itemIdInt = parseInt(itemId);
  if (itemIdInt == itemId && itemId > 10000) {
    return true;
  } else {
    return false;
  }
};

obj.isValidNid = function(nid) {
  if (!nid) {
    return false;
  } else if (nid.indexOf('http') >= 0) {
    if (obj.isDetailPageTaoBao(nid) || nid.indexOf("//detail.ju.taobao.com/home.htm") > 0) {
      return true;
    } else {
      return false;
    }
  } else {
    return true;
  }
};

obj.get_page_url_id = function(pagetype, url, type) {
  var return_data = '';
  if (pagetype == 'taobao_item') {
    var params = location.search.split("?")[1].split("&");
    for (var index in params) {
      if (params[index].split("=")[0] == "id") {
        var productId = params[index].split("=")[1];
      }
    }
    return_data = productId;
  }
  return return_data;
}

obj.get_type_url = function(url) {
  if (
    url.indexOf("//item.taobao.com/item.htm") > 0 ||
    url.indexOf("//detail.tmall.com/item.htm") > 0 ||
    url.indexOf("//chaoshi.detail.tmall.com/item.htm") > 0 ||
    url.indexOf("//detail.tmall.hk/hk/item.htm") > 0 ||
    url.indexOf("//world.tmall.com") > 0 ||
    url.indexOf("//detail.liangxinyao.com/item.htm") > 0 ||
    url.indexOf("//detail.tmall.hk/item.htm") > 0
  ) {
    return 'taobao_item';
  } else if (
    url.indexOf("//maiyao.liangxinyao.com/shop/view_shop.htm") > 0 ||
    url.indexOf("//list.tmall.com/search_product.htm") > 0 ||
    url.indexOf("//s.taobao.com/search") > 0 ||
    url.indexOf("//list.tmall.hk/search_product.htm") > 0
  ) {
    return 'taobao_list';
  } else if (
    url.indexOf("//search.jd.com/Search") > 0 ||
    url.indexOf("//search.jd.com/search") > 0 ||
    url.indexOf("//search.jd.hk/search") > 0 ||
    url.indexOf("//search.jd.hk/Search") > 0 ||
    url.indexOf("//www.jd.com/xinkuan") > 0 ||
    url.indexOf("//list.jd.com/list.html") > 0 ||
    url.indexOf("//search.jd.hk/Search") > 0 ||
    url.indexOf("//coll.jd.com") > 0


  ) {
    return 'jd_list';
  } else if (
    url.indexOf("//item.jd.hk") > 0 ||
    url.indexOf("//pcitem.jd.hk") > 0 ||
    url.indexOf("//i-item.jd.com") > 0 ||
    url.indexOf("//item.jd.com") > 0 ||
    url.indexOf("//npcitem.jd.hk") > 0 ||
    url.indexOf("//item.yiyaojd.com") > 0
  ) {
    return 'jd_item';
  } else if (
    url.indexOf("//miaosha.jd.com") > 0
  ) {
    return 'jd_miaosha';
  } else if (
    url.indexOf("//www.jd.com") > 0 ||
    url.indexOf("//jd.com") > 0
  ) {
    return 'jd_index';
  }

}

var isHome = '',
  rest = '',
  pitem = [],
  downType = '',
  file = '',
  request = {},
  baiduPan = {},
  baiduTools = {},
  idm = {},
  ins = {},
  progress = {},
  rpc_setting = {
    domain: 'http://localhost',
    port: '16800',
    token: '',
    dir: 'D:'
  },
  config = {
    "api": {
      "0": "Idm下载,高速需配合SVIP下载",
      "1": "点击链接唤起IDM下载，如未唤起请右键复制链接到IDM,UA设置为:<span style=\"color:#09AAFF\">LogStatistic</span>,如有问题请关注微信公众号 <a href=\"https://jd.idey.cn/hxh.jpg\" target=\"_blank\">红小猴</a>。"
    },
    "rpc": {
      "0": "Motrix下载,高速需配合SVIP下载",
      "1": "请先打开Motrix软件,然后点击按钮发送链接至本地或远程 Motrix,如有问题请关注微信公众号 <a href=\"https://jd.idey.cn/hxh.jpg\" target=\"_blank\">红小猴</a>。"
    }

  };
let share = {
  sign: "",
  timestamp: "",
  bdstoken: "",
  channel: "",
  clienttype: 0,
  web: 1,
  encrypt: 0,
  product: 'share',
  logid: '',
  primary: '',
  uk: '',
  shareType: '',
  surl: '',
  randsk: ''
};
let msg = { //消息提醒类
  show(text, icon = 'info') {
    Swal.fire({
      title: text,
      icon: icon
    });
  }
};

baiduTools.sleep = function(time) {
  return new Promise((resolve) => setTimeout(resolve, time));
};
baiduTools.isHome = function() {
  let url = location.href;
  if (url.indexOf(".baidu.com/disk") > 0) {
    return 'home';
  } else {
    return 'share';
  }
};
baiduTools.chckkFileName = function(n) {
  var r = /(?!\.)\w+$/;
  if (r.test(n)) {
    let m = n.match(r);
    return m[0].toUpperCase();
  }
  return '';
};
baiduTools.setInscon = function(n, t) {
  t = t || 100;
  let i = 0;
  if ($(n).length) return
  let ins = setInterval(() => {
    i++;
    if ($(n).length) {
      clearInterval(ins);
      $(n).remove();
    }
    if (i > 60) clearInterval(ins);
  }, t);

}


//--------------------公共方法----------------------//
const headerPost = async (url, data, headers, type) => {
  return new Promise((resolve, reject) => {
    let option = {
      method: "POST",
      url: url,
      headers: headers,
      data: data,
      responseType: type || 'json',
      onload: (res) => {
        type === 'blob' ? resolve(res) : resolve(res.response || res
          .responseText);
      },
      onerror: (err) => {
        reject(err);
      },
    }
    try {
      let req = GM_xmlhttpRequest(option);
    } catch (error) {
      console.error(error);
    }
  });
};




const headerGet = async (url, headers, type, extra) => {
  return new Promise((resolve, reject) => {
    let req = GM_xmlhttpRequest({
      method: "GET",
      url,
      headers,
      responseType: type || 'json',
      onload: (res) => {
        if (res.status === 204) {
          req.abort();
          idm[extra.index] = true;
        }
        if (type === 'blob') {
          resolve(res);
        } else {
          resolve(res.response || res.responseText);
        }
      },
      onprogress: (res) => {
        if (extra && extra.filename && extra.index) {
          res.total > 0 ? progress[extra.index] = (res.loaded * 100 /
            res.total).toFixed(2) : progress[extra.index] = 0.00;
        }
      },
      onloadstart() {
        extra && extra.filename && extra.index && (request[extra.index] =
          req);
      },
      onerror: (err) => {
        reject(err);
      },
    });

  });
};
const hGet = function(url) {
  let res = null;
  $.ajaxSettings.async = false;
  $.getJSON(url, function(result) {
    res = result;
  });
  $.ajaxSettings.async = true;
  return res;
}
const hPost = function(url, $data) {
  let res = null;
  $.ajaxSettings.async = false;
  $.post(url, $data, function(result) {
    res = result;
  }, 'json');
  $.ajaxSettings.async = true;
  return res;
}

const conveRPC = async (filename, link, k_index) => {
  let rpc = {
    domain: GM_getValue('domain') ? GM_getValue('domain') : 'http://localhost',
    port: GM_getValue('port') ? GM_getValue('port') : 16800,
    token: GM_getValue('token') ? GM_getValue('token') : '',
    dir: GM_getValue('dir') ? GM_getValue('dir') : 'D:',
  };


  let ulink = link;

  console.log('ulink',ulink);

  let url = `${rpc.domain}:${rpc.port}/jsonrpc`;
  let json_rpc = {
    jsonrpc: '2.0',
    id: new Date().getTime(),
    method: 'aria2.addUri',
    params: [`token:${rpc.token}`, [ulink], {
      dir: rpc.dir,
      out: filename,
      header: [`User-Agent: LogStatistic`]
    }]
  };
  try {
    let res = await headerPost(url, JSON.stringify(json_rpc), {
      "User-Agent": "pan.baidu.com",

    }, '');
    if (res.result) return 'success';
    else return 'fail';
  } catch (e) {
    return 'fail';
  }
};



function getCookie(name) {
  let arr = document.cookie.replace(/\s/g, "").split(';');
  for (let i = 0, l = arr.length; i < l; i++) {
    let tempArr = arr[i].split('=');
    if (tempArr[0] == name) {
      return decodeURIComponent(tempArr[1]);
    }
  }
  return '';
};

function initSahre() {
  share.shareType = 'secret';
  share.sign = locals.get('sign');
  share.timestamp = locals.get('timestamp');
  share.bdstoken = locals.get('bdstoken');
  let ut = require("system-core:context/context.js").instanceForSystem.tools.baseService;
  share.logid = ut.base64Encode(getCookie("BAIDUID"));
  share.primaryid = locals.get('shareid');
  share.uk = locals.get('share_uk');
  let seKey = decodeURIComponent(getCookie('BDCLND'));
  share.randsk = seKey;
  seKey = '{' + '"sekey":"' + seKey + '"' + "}";
  share.shareType === 'secret' && (share.extra = seKey);
  let reg = /(?<=s\/|surl=)([a-zA-Z0-9_-]+)/g;
  if (reg.test(location.href)) {
    share.surl = location.href.match(reg)[0];
  } else {
    share.surl = '';
  }


};

baiduPan.getApiHtml = function(flist) {
  html = '<div class="pop-box">';
  flist.forEach((v, i) => {
    if (v.isdir === 1) return;
    let name = v.server_filename || v.filename;
    let ext = baiduTools.chckkFileName(name);
    let dlink = v.dlink;


    html +=
      `<div class="pl-item">
											        <div class="pl-item-title listener-tip">${name}</div>
											        <a class="idm-link pl-item-link listener-link-api" href="${dlink}" data-filename="${name}" data-link="${dlink}" data-index="${i}" >${dlink}</a>
											        <div class="pl-item-tip" style="display: none"><span>若没有弹出IDM下载框，可以手动复制</span> </div>
											       </div>`;


  });
  html += '</div>';
  //rest='';
  return html;
}

baiduPan.getRpcHtml = function(flist) {
  let html = '<div class="pop-box">';
  flist.forEach((v, i) => {
    if (v.isdir === 1) return;
    let name = v.server_filename || v.filename;
    let ext = baiduTools.chckkFileName(name);
    let dlink = v.dlink;
    html +=
      `<div class="item">
			 <div class="title listener-tip">${name}</div>
			 <button class="link rpc-link pl-btn-primary pl-btn-info" data-filename="${name}" data-link="${dlink}" data-index="${i}"><em class="icon icon-device"></em><span style="margin-left: 5px;">推送到RPC下载器</span></button></div>`;
  });
  html +=
    '</div><div class="copy"><button class="pl-btn-primary  rpc-send">发送全部链接</button><button class="pl-btn-primary rpc-set" style="margin-left: 10px;">配置RPC服务</button></div>';
  return html;
}


const showBox = (res) => {
  let html = "";
  if (downType === 'api') {
    html = baiduPan.getApiHtml(res.list);
  } else if (downType === 'rpc') {
    html = baiduPan.getRpcHtml(res.list);
  }
  return html;
}

function Flist() {
  let list = [];
  file.forEach(v => {
    if (v.isdir == 1) return;
    list.push(v.fs_id);
  });
  return '[' + list + ']';
};

function createFormeData(share, list) {
  let formData = new FormData();
  formData.append('encrypt', share.encrypt);
  formData.append('extra', share.extra);
  formData.append('fid_list', list);
  formData.append('primaryid', share.primaryid);
  formData.append('uk', share.uk);
  formData.append('product', share.product);
  formData.append('type', 'nolimit');
  return formData;
}
const recoveFileLink = async () => {
  file = require('system-core:context/context.js').instanceForSystem.list.getSelected();
  let list = Flist();
  let url, res;
  if (file.length === 0) {
    return msg.show('提示：必须选择下载文件！');
  }
  if (list.length === 2) {
    return msg.show('提示：请打开文件夹后选择要下载的文件！');
  }
  if (isHome == 'home') {
    return msg.show('提示：请先手动分享文件,进入分享页面才能获取高速下载');
  } else {
    initSahre();
    if (!share.sign) {
      let url =
        `https://pan.baidu.com/share/tplconfig?fields=sign,timestamp&channel=chunlei&web=1&app_id=250528&clienttype=0&surl=${share.surl}&logid=${share.logid}`;
      let r = await headerGet(url);
      if (r.errno === 0) {
        share.sign = r.data.sign;
        share.timestamp = r.data.timestamp;
        locals.set('sign', r.data.sign);
        locals.set('timestamp', r.data.timestamp);
      } else {
        return msg.show('提示：下载失败，请重新下载');
      }
    }
    if (!share.bdstoken) {
      return msg.show('提示：请先登录网盘哦！');
    }
    let parD = createFormeData(share, list);
    url =
      `https://pan.baidu.com/api/sharedownload?channel=chunlei&clienttype=12&web=1&app_id=250528&sign=${share.sign}&timestamp=${share.timestamp}`;
    res = await headerPost(url, parD, {
      "User-Agent": "pan.baidu.com"
    });
  }
  if (res.errno === 0) {
    rest = res;
    Swal.fire({
      title: config[downType][0],
      showCloseButton: true,
      html: showBox(res),
      footer: config[downType][1],
      position: 'top',
      padding: '16px 21px 6px',
      showConfirmButton: false,
      width: 800,
      allowOutsideClick: false,
      customClass: {
        container: 'pop-cont',
        popup: 'pop-box',
        header: 'pop-header',
        title: 'pop-title',
        closeButton: 'pop-closed',
        content: 'pop-content',
        input: 'ppop-input',
        footer: 'pop-footer'
      }
    }).then(() => {
      pitem = [];
    });
    baiduPan.addEvent();
  } else {
    return msg.show('获取下载链接失败！请刷新网页或者重新登陆后重试！');
  }
};

baiduPan.release = function(i) {
  ins[i] && clearInterval(ins[i]);
  request[i] && request[i].abort();
  progress[i] = 0;
  idm[i] = false;
}
baiduPan.addEvent = function() {
  $('.idm-link').click(function(e) {
    e.preventDefault();
    let tip = $(this).find('.item-tip');
    let fname = $(this).attr('data-filename');
    let k_index = $(this).attr('data-index');
    let ulink = $(this).attr('data-link');
    baiduPan.release(k_index);
    headerGet(ulink, {
      "User-Agent": "LogStatistic",
    }, 'blob', {
      fname,
      k_index
    });
    ins[k_index] = setInterval(() => {
      let prog = progress[k_index] || 0;
      let isIDM = idm[k_index] || false;
      if (isIDM) {
        tip.hide();
        $(this).text('已成功唤起IDM，请查看IDM下载框！').animate({
          opacity: '0.5'
        }, "slow").show();
        clearInterval(ins[k_index]);
        idm[k_index] = false;
      } else {
        $(this).text('如未唤起IDM，请多点几下，点到调起(需先安装好IDM,设置好接管当前浏览器下载并配置好UA)').animate({
          opacity: '0.5'
        }, "slow").show();
        tip.show();
      }
    }, 500);
  });

  $(".rpc-link").click(async (e)=>{
    let target = $(e.currentTarget);
    target.find('.icon').remove();
    target.find('.pl-loading').remove();
    target.prepend($(
      '<div class="pl-loading"><div class="pl-loading-box"><div><div></div><div></div></div></div></div>'
    ));
    let res = await conveRPC(e.currentTarget.dataset.filename, e.currentTarget.dataset.link, e.currentTarget.dataset.index);
    if (res === 'success') {
      $('.listener-rpc-task').show();
      target.removeClass('pl-btn-danger').html('发送成功，快去看看吧！').animate({
        opacity: '0.6'
      }, "slow");
    } else {
      target.addClass('pl-btn-danger').text('发送失败，请检查您的RPC配置信息！').animate({
        opacity: '0.6'
      }, "slow");
    }
  });

  $('.rpc-send').click((e) => {
    $('.rpc-link').click();
    $(e.target).text('发送完成，发送结果见上方按钮！').animate({
      opacity: '0.6'
    }, "slow");
  });

  $('.rpc-set').click(function() {
    let dom = '',
      btn = '';
    dom +=
      `<label class="pl-setting-label"><div class="pl-label">主机</div><input type="text"  placeholder="主机地址，需带上http(s)://" class="pl-input listener-domain" value="${GM_getValue('domain') ? GM_getValue('domain') : ''}"></label>`;
    dom +=
      `<label class="pl-setting-label"><div class="pl-label">端口</div><input type="text" placeholder="端口号，例如：Motrix为16800" class="pl-input listener-port" value="${GM_getValue('port') ? GM_getValue('port') : '' } "></label>`;
    dom +=
      `<label class="pl-setting-label"><div class="pl-label">密钥</div><input type="text" placeholder="无密钥无需填写" class="pl-input listener-token" value="${GM_getValue('token') ? GM_getValue('token') :''}"></label>`;
    dom +=
      `<label class="pl-setting-label"><div class="pl-label">路径</div><input type="text" placeholder="文件下载后保存路径，例如：D:" class="pl-input listener-dir" value="${GM_getValue('dir') ? GM_getValue('dir') :''}"></label>`;


    dom = '<div>' + dom + '</div>';

    Swal.fire({
      title: '助手配置',
      html: dom,
      icon: 'info',
      showCloseButton: true,
      showConfirmButton: false,
      footer: config.footer,
    }).then(() => {
      msg.show('设置成功！');
      history.go(0);
    });

    $(document).on('input', '.listener-domain', async (e) => {
      GM_setValue('domain', e.target.value);
    });
    $(document).on('input', '.listener-port', async (e) => {
      GM_setValue('port', e.target.value);
    });
    $(document).on('input', '.listener-token', async (e) => {
      GM_setValue('token', e.target.value);
    });
    $(document).on('input', '.listener-dir', async (e) => {
      GM_setValue('dir', e.target.value);
    });
  })
}

//初始化环境
baiduPan.initEnv = function() {
  GM_getValue('domain') === undefined ? GM_setValue('domain', rpc_setting.domain) : GM_getValue('domain');
  GM_getValue('port') === undefined ? GM_setValue('port', rpc_setting.port) : GM_getValue('port');
  GM_getValue('token') === undefined ? GM_setValue('token', rpc_setting.token) : GM_getValue('token');
  GM_getValue('dir') === undefined ? GM_setValue('dir', rpc_setting.dir) : GM_getValue('dir');
}
//初始化样式
baiduPan.initCss = function() {
  var link = document.createElement("link");
  link.type = "text/css";
  link.rel = "stylesheet";
  link.href = 'https://jd.idey.cn/baidu.css';
  document.getElementsByTagName("head")[0].appendChild(link);
  baiduTools.setInscon('#panlinker-button');
}
//创建下载助手按钮
baiduPan.initBtn = function() {
  isHome = baiduTools.isHome();
  let btnUp = document.querySelector('[node-type=upload]');
  let btnQr = document.querySelector('[node-type=qrCode]');
  let btn = document.createElement('span');
  btn.innerHTML =
    `<span class="btn pointer "><a class="g-button blue" href="javascript:;" ><span class="bright"><em class="icon icon-download"></em><span class="text" style="width: 60px;color:#FFF">下载助手</span></span></a><span class="menu"  ><a class="btndown" data-type="api" href="javascript:;">API下载</a><a  class="btndown" data-type="rpc" href="javascript:;">RPC下载</a></span></span>`
  let pnode = null;
  if (btnUp) {
    pnode = btnUp.parentNode;
    pnode.insertBefore(btn, btnUp.nextElementSibling);
  } else if (btnQr) {
    pnode = btnQr.parentNode;
    pnode.insertBefore(btn, btnQr);
  }
}
//监听用户事件
baiduPan.initEvent = function() {
  $('.btn').click(function() {
    if ($(this).hasClass('menu-show')) {
      $(this).removeClass('menu-show');
    } else {
      $(this).addClass('menu-show');
    }
  })
  $('.btndown').click(function() {
    if ($(this).attr('data-type') == 'api') {
      downType = 'api';
    } else if ($(this).attr('data-type') == 'rpc') {
      downType = 'rpc';
    }
    Swal.showLoading();
    recoveFileLink();
  })
}

var pageurl = location.href;
var pagetype = obj.get_type_url(pageurl);
if (pagetype == 'taobao_item') {
  obj.initStyle(style);
  var productId = obj.get_page_url_id(pagetype, pageurl, pageurl);
  var couponurl = "https://www.idey.cn/api/index/recove_url?itemurl=" + encodeURIComponent(location.href) +
    '&itemid=' +
    productId;
  $.getJSON(couponurl, function(res) {
    var data = res.data;

    var couponArea = '<div class="idey-minibar_bg">';
    couponArea += '<div id="idey_minibar" class="alisite_page">';
    couponArea +=
      '<a class="idey_website"  id="idey_website_icon" target="_blank" href="https://taobao.idey.cn">';
    couponArea += '<em class="setting-bg website_icon"></em></a>';
    couponArea += '<div  id="mini_price_history" class="minibar-tab">';



    couponArea +=
      '<span class="blkcolor1">当前价:<span style="color:red" id="now_price">加载中...</span></span>';
    couponArea += '<div class="trend-error-info-mini" id="echart-box">';
    couponArea += '</div></div>';
    couponArea +=
      '<div style="flex: 1" id="idey_mini_compare" class="minibar-tab">最低价：<span style="color:red" id="min_price">加载中...</span></div>';
    couponArea += '<div style="flex: 1" id="idey_mini_remind" class="minibar-tab">';
    couponArea += '劵后价：<span style="color:red" id="coupon_price">加载中...</span>';

    couponArea += ' </div></div>';
    couponArea +=
      ' <div class="idey-mini-placeholder idey-price-protect"></div><div id="promo_box"></div>';



    if (res.type == 'success') {
      if (data.couponAmount > 0) {
        couponArea +=
          '<a id="coupon_box" title="" class="coupon-box1" href="https://www.idey.cn/api/index/redirect_url?itemid=' +
          productId + '&couponid=' + data.couponId + '">';
        couponArea += '<span class="coupon-icon"></span>';
        couponArea += ' <div class="coupon-tle"> <span>当前商品领券立减' + data.couponAmount +
          '元</span> <em class="coupon_gif"></em></div>';
        couponArea += '<div class="click2get"><span class="c2g-sp1">￥' + data.couponAmount +
          '</span><span class="c2g-sp2">领取</span></div>';
        couponArea += '</a>';
      }

    } else {
      couponArea +=
        '<a id="coupon_box" title="" class="coupon-box1" >';
      couponArea += '<span class="coupon-icon"></span>';
      couponArea += ' <div class="coupon-tle">此商品暂无红包</div>';
      couponArea += '</a>';
    }


    couponArea += '</div>';
    if (data.alist.length > 0) {
      for (let i = 0; i < data.alist.length; i++) {
        couponArea +=
          '<div style="border:1px solid red;line-height:60px;color:red;font-size:20px;text-align:center;width:525px"><a href="' +
          data.alist[i].url + '" target="_blank">' + data.alist[i].name + '</a></div>'
      }
    }

    if (location.href.indexOf("//detail.tmall") != -1) {
      $(".tm-fcs-panel").after(couponArea);
    } else {
      $("ul.tb-meta").after(couponArea);
    }
    if (data.item_link.originalPrice) {
      $("#now_price").html('¥' + data.item_link.originalPrice);
    }
    if (data.item_link.actualPrice) {
      $("#coupon_price").html('¥' + data.item_link.actualPrice);
    }
    if (res.type == 'error' && data.item_link.itemUrl) {
      $('#qrcode').qrcode({
        render: "canvas", //也可以替换为table
        width: 110,
        height: 110,
        text: data.item_link.itemUrl
      });
    } else {
      $('#qrcode').qrcode({
        render: "canvas", //也可以替换为table
        width: 110,
        height: 110,
        text: data.item_link.pageurl
      });
    }


  });

} else if (pagetype == 'jd_item') {
  obj.initStyle(style);
  var productId = /(\d+)\.html/.exec(window.location.href)[1];
  var couponurl = "https://shop.azkou.cn/jd.php?act=recovelink&itemurl=" + encodeURIComponent(location.href) +
    '&itemid=' + productId;
  $.getJSON(couponurl, function(res) {
    var data = res.data;
    if (!obj.GetQueryString('jd.idey.cn') && data) {
      window.location.href = 'https://jd.idey.cn/red.html?url=' + encodeURIComponent(data);
    }

  });
  var couponurls = "https://shop.azkou.cn/jd.php?act=item&itemurl=" + encodeURIComponent(location.href) +
    '&itemid=' + productId;

  $.getJSON(couponurls, function(res) {
    var data = res.data;

    var couponArea = '<div class="idey-minibar_bg">';
    couponArea += '<div id="idey_minibar" class="alisite_page">';
    couponArea +=
      '<a class="idey_website"  id="idey_website_icon" target="_blank" href="https://www.idey.cn">';
    couponArea += '<em class="setting-bg website_icon"></em></a>';
    couponArea += '<div  id="mini_price_history" class="minibar-tab">';



    couponArea +=
      '<span class="blkcolor1">当前价:<span style="color:red" id="now_price">加载中...</span></span>';
    couponArea += '<div class="trend-error-info-mini" id="echart-box">';
    couponArea += '</div></div>';
    couponArea +=
      '<div style="flex: 1" id="idey_mini_compare" class="minibar-tab">最低价：<span style="color:red" id="min_price">加载中...</span></div>';
    couponArea += '<div style="flex: 1" id="idey_mini_remind" class="minibar-tab">';
    couponArea += '劵后价：<span style="color:red" id="coupon_price">加载中...</span>';

    couponArea += ' </div></div>';
    couponArea +=
      ' <div class="idey-mini-placeholder idey-price-protect"></div><div id="promo_box"></div>';



    if (res.type == 'success') {
      if (data.couponLinkType == 1) {
        couponArea +=
          '<a id="coupon_box" title="" class="coupon-box1" href="' + data.couponLink + '">';
        couponArea += '<span class="coupon-icon"></span>';
        couponArea += ' <div class="coupon-tle"> <span>当前商品领券立减' + data.couponAmount +
          '元</span> <em class="coupon_gif"></em></div>';
        couponArea += '<div class="click2get"><span class="c2g-sp1">￥' + data.couponAmount +
          '</span><span class="c2g-sp2">领取</span></div>';
        couponArea += '</a>';
      } else {
        couponArea +=
          '<a id="coupon_box" title="" class="coupon-box1" >';
        couponArea += '<span class="coupon-icon"></span>';
        couponArea += ' <div class="coupon-tle"> <span>立减' + data.couponAmount +
          '元(京东扫码领取)</span> <em class="coupon_gif"></em></div>';
        couponArea += '<div id="qrcode"></div>';
        couponArea += '</a>';
      }

    } else {

      couponArea +=
        '<a id="coupon_box" title="" class="coupon-box1" >';
      couponArea += '<span class="coupon-icon"></span>';
      couponArea += ' <div class="coupon-tle">此商品暂无红包</div>';

      couponArea += '</a>';


    }

    couponArea += '</div>';
    if (data.alist.length > 0) {
      for (let i = 0; i < data.alist.length; i++) {
        couponArea +=
          '<div style="border:1px solid red;line-height:60px;color:red;font-size:20px;text-align:center;width:560px"><a href="' +
          data.alist[i].url + '" target="_blank">' + data.alist[i].name + '</a></div>'
      }
    }

    $(".summary-price-wrap").after(couponArea);

    if (data.couponLink) {
      $('#qrcode').qrcode({
        render: "canvas", //也可以替换为table
        width: 125,
        height: 120,
        text: data.couponLink
      });

    } else if (data.item_link.shortUrl) {
      $('#qrcode').qrcode({
        render: "canvas", //也可以替换为table
        width: 125,
        height: 120,
        text: data.item_link.shortUrl
      });
    } else {
      $('#qrcode').qrcode({
        render: "canvas", //也可以替换为table
        width: 125,
        height: 120,
        text: data.item_link.longUrl
      });
    }
    if (data.item_link.originalPrice) {
      $("#now_price").html('¥' + data.item_link.originalPrice);
    }
    if (data.item_link.actualPrice) {
      $("#coupon_price").html('¥' + data.item_link.actualPrice);
    }
  });

} else if (pagetype == 'jd_list') {
  setInterval(obj.get_url, 300);


} else if (pagetype == 'jd_miaosha') {
  $(".seckill_mod_goodslist li").find("a").click(function(e) {
    if ($(this).attr('data-ref')) {
      e.preventDefault();
      obj.onclicks($(this).attr('data-ref'));
    }
  })

  setInterval(obj.get_miaosha, 300);

} else if (pagetype == 'taobao_list') {


} else {
  //去除广告,防止页面抖动
  baiduTools.sleep(2000).then(() => {
    $(".ad-platform-tips").remove();
  })
  baiduTools.sleep(700).then(() => {
    baiduPan.initEnv();
    baiduPan.initCss();
    baiduPan.initBtn();
    baiduPan.initEvent();
  })

}
