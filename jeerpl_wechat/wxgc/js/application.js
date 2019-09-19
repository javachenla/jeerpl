/** 全局 */
var env = {
	"api_prod": "http://140.249.17.114:8081/pen", //正式环境
	"api_dev": "http://192.168.2.120:8080/pen", //开发环境
	"api_test": "http://192.168.2.120:8080/pen", //测试环境
	"version": "http://140.249.17.114:8081/apk/update.json", //版本信息
	"apk": "http://140.249.17.114:8081/apk/nurse.apk", //下载路径
	getUrl: function() { //使用路径
		return this.api_prod;
	},
	/** 时间格式化 */
	formatDate: function(now) {
		if(now == null) {
			return '';
		} else {
			var date = new Date(now);
			var seperator1 = "-";
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			var strDate = date.getDate();
			if(month >= 1 && month <= 9) {
				month = "0" + month;
			}
			if(strDate >= 0 && strDate <= 9) {
				strDate = "0" + strDate;
			}
			var currentdate = year + seperator1 + month + seperator1 + strDate;
			return currentdate;
		}
	},
	getPageSize: function() {
		return 10;
	},
	getHeaders: function() {
		var headers = {
			'accessToken': localStorage.getItem("r_token"),
			username: localStorage.getItem("r_userName"),
			tendpos:localStorage.getItem("r_tendPos"),
			groupDb: "pensionytxl"
		}
		return headers;
	},
	getUrlParam: function(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if(r != null) return unescape(r[2]);
		return null;
	}
};

//var picURL = "http://140.249.17.51:8081";
//var URL = env.api_dev;

function trim(str) { //删除左右两端的空格
	if(str) {
		return str.replace(/(^\s*)|(\s*$)/g, "");
	}
}
//返回
function getCurrentDate() {
	var curDate = new Date();
	var y = curDate.getFullYear();
	var m = curDate.getMonth() + 1;
	var d = curDate.getDate();

	if(d < 10) {
		d = "0" + d;

	}
	if(m < 10) {
		m = "0" + m;

	}
	var datetime = y + '-' + m + '-' + d;
	return datetime;

}

function isPhone(aPhone) {
	var bValidate = RegExp(/^(0|86|17951)?(13[0-9]|15[012356789]|18[0-9]|17[0678]|14[57])[0-9]{8}$/).test(aPhone);
	if(bValidate) {
		return true;
	} else
		return false;
}

function GetQueryString(name) {

	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg); //获取url中"?"符后的字符串并正则匹配
	var context = "";
	if(r != null) {
		context = r[2];
		context = decodeURI(context);
	}
	reg = null;
	r = null;
	return context == null || context == "" || context == "undefined" ? "" : context;
}
//显示加载数据
function ShowDiv() {
	$("#loading").show();
}

//隐藏加载数据
function HiddenDiv() {
	$("#loading").hide();
}

function IdentityCodeValid(code) {
	var city = {
		11: "北京",
		12: "天津",
		13: "河北",
		14: "山西",
		15: "内蒙古",
		21: "辽宁",
		22: "吉林",
		23: "黑龙江 ",
		31: "上海",
		32: "江苏",
		33: "浙江",
		34: "安徽",
		35: "福建",
		36: "江西",
		37: "山东",
		41: "河南",
		42: "湖北 ",
		43: "湖南",
		44: "广东",
		45: "广西",
		46: "海南",
		50: "重庆",
		51: "四川",
		52: "贵州",
		53: "云南",
		54: "西藏 ",
		61: "陕西",
		62: "甘肃",
		63: "青海",
		64: "宁夏",
		65: "新疆",
		71: "台湾",
		81: "香港",
		82: "澳门",
		91: "国外 "
	};
	var tip = "";
	var pass = true;

	if(!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)) {
		tip = "身份证号格式错误";
		pass = false;
	} else if(!city[code.substr(0, 2)]) {
		tip = "地址编码错误";
		pass = false;
	} else {
		//18位身份证需要验证最后一位校验位
		if(code.length == 18) {
			code = code.split('');
			//∑(ai×Wi)(mod 11)
			//加权因子
			var factor = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
			//校验位
			var parity = [1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2];
			var sum = 0;
			var ai = 0;
			var wi = 0;
			for(var i = 0; i < 17; i++) {
				ai = code[i];
				wi = factor[i];
				sum += ai * wi;
			}
			var last = parity[sum % 11];
			if(parity[sum % 11] != code[17]) {
				tip = "校验位错误";
				pass = false;
			}
		}
	}
	if(!pass) alert(tip);
	return pass;
}

function defPic(obj) {
	obj.src = "../images/ic_launcher.png";
}

function jsXss(s) {
	var pattern = new RegExp("[%--`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
	var rs = "";
	for(var i = 0; i < s.length; i++) {
		rs = rs + s.substr(i, 1).replace(pattern, '');
	}
	return rs;

}

function setCookie(name, value, Days) {
	var exp = new Date();

	//          dhhdhd
	exp.setTime(exp.getTime() + Days * 60 * 60 * 1000);
	document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}

function getCookie(name) {
	var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");

	if(arr = document.cookie.match(reg))
		return unescape(arr[2]);
	else
		return null;
}

function delCookie(name) { //删除cookie
	setCookie(name, "", -1);
}

function showLocation(position) {
	var latitude = position.coords.latitude;
	var longitude = position.coords.longitude;
	setCookie("latitude", latitude, 1);
	setCookie("longitude", longitude, 1);
	localStorage.setItem("smk_lon", longitude);
	localStorage.setItem("smk_lat", latitude);
}

function errorHandler(err) {
	if(err.code == 1) {
		console.dir("拒绝访问当前位置!");
	} else if(err.code == 2) {
		console.dir("您的位置获取不到!");
	}
}

function getLocation() {
	if(navigator.geolocation) {
		// timeout at 60000 milliseconds (60 seconds)
		var options = {
			timeout: 60000
		};
		navigator.geolocation.getCurrentPosition(showLocation, errorHandler, options);
	} else {
		alert("浏览器不支持定位!");
	}
}