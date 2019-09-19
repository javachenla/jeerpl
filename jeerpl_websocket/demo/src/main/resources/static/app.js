var stompClient = null;
 
function connect() {
    var socket = new SockJS('/endpoint-websocket'); //连接上端点(基站)
    
    stompClient = Stomp.over(socket);			//用stom进行包装，规范协议
    stompClient.connect({}, function (frame) {	
       
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/game_chat', function (result) {
        	var res=JSON.parse(result.body);
        	 
        	alert(res.content);
        });
    });
}
  

$(function () {
     connect(); 
});

