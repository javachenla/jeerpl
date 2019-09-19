/*!
 * ======================================================
 * FeedBack Template For MUI (http://dev.dcloud.net.cn/mui)
 * =======================================================
 * @version:1.0.0
 * @author:cuihongbao@dcloud.io
 */
(function() {
	var index = 1;
	var size = null;
	var imageIndexIdNum = 0;
	var starIndex = 0;
	var feedback = {
		nurseName: document.getElementById('nurseName'), //护工姓名
		psnHid: document.getElementById('psnHid'), //档案id
		psnName: document.getElementById('psnName'), //老人姓名
		eventType: document.getElementById('eventType'), //事件类型
		eventDesc: document.getElementById('eventDesc'), //事件描述
		imageList: document.getElementById('image-list'),//上传图片
		submitBtn: document.getElementById('submit')//提交
	};
	var url = env.api_prod;
	feedback.files = [];
	feedback.uploader = null;  
	feedback.deviceInfo = null; 
	/**
	 *提交成功之后，恢复表单项 
	 */
	feedback.clearForm = function() {
		feedback.nurseName.value = '';
		feedback.psnHid.value = '';
		feedback.psnName.value = '';
		feedback.eventType.value = '';
		feedback.eventDesc.value = '';
		feedback.imageList.innerHTML = '';
		feedback.newPlaceholder();
		feedback.files = [];
		index = 0;
		size = 0;
		imageIndexIdNum = 0;
		starIndex = 0;
	};
	feedback.getFileInputArray = function() {
		return [].slice.call(feedback.imageList.querySelectorAll('.file'));
	};
	feedback.addFile = function(path) {
		feedback.files.push({name:"images"+index,path:path,id:"img-"+index});
		index++;
	};
	feedback.submitBtn.addEventListener('tap', function(event) {
		if (feedback.eventType.value == ''||feedback.eventDesc.value == '') {
			return mui.toast('请填事件描述');
		}
		if (feedback.eventDesc.value.length > 1000) {
			return mui.toast('信息超长,请重新填写~')
		}
		//判断网络连接
		if(plus.networkinfo.getCurrentType()==plus.networkinfo.CONNECTION_NONE){
			return mui.toast("连接网络失败，请稍后再试");
		}
		feedback.send(mui.extend({}, feedback.deviceInfo, {
			nurseName:feedback.nurseName.value,
			psnHid:feedback.psnHid.value,
			psnName:feedback.psnName.value,
			eventType:feedback.eventType.value,
			eventDesc:feedback.eventDesc.value,
			images: feedback.files
		})) 
	}, false)
	/**
	 * 初始化图片域占位
	 */
	feedback.newPlaceholder = function() {
		var fileInputArray = feedback.getFileInputArray();
		if (fileInputArray &&
			fileInputArray.length > 0 &&
			fileInputArray[fileInputArray.length - 1].parentNode.classList.contains('space')) {
			return;
		};
		imageIndexIdNum++;
		var placeholder = document.createElement('div');
		placeholder.setAttribute('class', 'image-item space');
		var up = document.createElement("div");
		up.setAttribute('class','image-up')
		//删除图片
		var closeButton = document.createElement('div');
		closeButton.setAttribute('class', 'image-close');
		closeButton.innerHTML = 'X';
		closeButton.id = "img-"+index;
		//小X的点击事件
		closeButton.addEventListener('tap', function(event) {
			setTimeout(function() {
				for(var temp=0;temp<feedback.files.length;temp++){
					if(feedback.files[temp].id==closeButton.id){
						feedback.files.splice(temp,1);
					}
				}
				feedback.imageList.removeChild(placeholder);
			}, 0);
			return false;
		}, false);
		
		//
		var fileInput = document.createElement('div');
		fileInput.setAttribute('class', 'file');
		fileInput.setAttribute('id', 'image-' + imageIndexIdNum);
		fileInput.addEventListener('tap', function(event) {
			var self = this;
			var index = (this.id).substr(-1);
			plus.gallery.pick(function(e) {
//				console.log("event:"+e);
				var name = e.substr(e.lastIndexOf('/') + 1);
//				console.log("name:"+name);
				plus.zip.compressImage({
					src: e,
					dst: '_doc/' + name,
					overwrite: true,
					quality: 50
				}, function(zip) {
					size += zip.size  
//					console.log("filesize:"+zip.size+",totalsize:"+size);
					if (size > (10*1024*1024)) {
						return mui.toast('文件超大,请重新选择~');
					}
					if (!self.parentNode.classList.contains('space')) { //已有图片
						feedback.files.splice(index-1,1,{name:"images"+index,path:e});
					} else { //加号
						placeholder.classList.remove('space');
						feedback.addFile(zip.target);
						feedback.newPlaceholder();
					}
					up.classList.remove('image-up');
					placeholder.style.backgroundImage = 'url(' + zip.target + ')';
				}, function(zipe) {
					mui.toast('压缩失败！')
				});
			}, function(e) {
				mui.toast(e.message);
			},{});
		}, false);
		placeholder.appendChild(closeButton);
		placeholder.appendChild(up);
		placeholder.appendChild(fileInput);
		feedback.imageList.appendChild(placeholder);
	};
	feedback.newPlaceholder();
	feedback.send = function(content) {
		feedback.uploader = plus.uploader.createUpload(env.getUrl() + "/InterfaceApp?type=addPersonEvent", {
			method: 'POST'
		}, function(upload, status) {
//			console.log(JSON.stringify(upload.responseText))
			var data = $.parseJSON(upload.responseText)
//			plus.nativeUI.closeWaiting()
			if(data.errno==0){
				//上传成功，重置表单
				mui.toast('添加成功')
				feedback.clearForm();
				mui.back();
			}else{
				mui.toast("添加失败"+data.errMsg);
			}
			
		});
		//添加上传数据
		mui.each(content, function(index, element) {
			if (index !== 'images') {
//				console.log("addData:"+index+","+element);
				feedback.uploader.addData(index, element);
			} 
		});
		//添加上传文件
		mui.each(feedback.files, function(index, element) {
			var f = feedback.files[index];
//			console.log("addFile:"+JSON.stringify(f));
			feedback.uploader.addFile(f.path, {
				key: f.name
			});
		});
		feedback.uploader.setRequestHeader('accessToken',env.getHeaders().accessToken);
		feedback.uploader.setRequestHeader('username',env.getHeaders().username);
		feedback.uploader.setRequestHeader('groupDb',env.getHeaders().groupDb);
		//开始上传任务
		feedback.uploader.start();
//		mui.alert("提交成功","确定",function () {
//			feedback.clearForm();
//			mui.back();
//		});
//		plus.nativeUI.showWaiting();
	};
	
})();
