<%@ page language="java"  pageEncoding="utf-8"%>
<script>
    $(document).ready(function () {
        if($("#needAuth").val()=='true'){
            $("#authModal").modal({backdrop: 'static', keyboard: false});
            $("#authModal").modal('show');
        }
    });
    var waitTime = 60*2;
    // 增加手机验证码js
    function waitSendMsgAuth() {
        var i = waitTime--;
        $("#sendCode").text(i + "秒");
        if (i == 0) {
            clearMsgAuth();
        }
    }

    function sendCode(){
        $("#sendCode").attr("disabled","disabled");
        $("#tipMsg").text("正在发送短信...");
        $.ajax({
            type : "post",
            url : "/auth/sendCode",
            cache: false,
            async: true,
            data:"_t="+new Date().getTime(),
            timeout:600000,
            success : function(data){
                var status = data.status;
                if (status == true) {
                    timerID = setInterval("waitSendMsgAuth()", 1000);
                    $("#tipMsg").text("短信验证码已发送");
                } else {
                    $("#tipMsg").text(data.reason);
                    $("#sendCode").removeAttr("disabled");
                }
            }
        });
    }

    // 增加手机验证码js
    function clearMsgAuth() {
        clearInterval(timerID);
        waitTime = 60*2;
        $("#sendCode").html("获取验证码");
        $("#sendCode").removeAttr("disabled");
    }
    //var me = commonErp.getInstance();
    
    function submitCode() {
    	var authCode = $("#authCode").val();
    	
        var param = "authCode="+authCode + "&_t=" + new Date().getTime();
       	
        $.ajax({
              type : "post",
              url : "/auth/validateCode",
              cache: false,
              async: true,
              data:param,
              success : function(data){
            	  goMain(data);
              }
          });
    }

    function goMain(data) {
    	var status = data.status;
      
        if (status == true) {
        	
        	var locationUrl = window.location;

        	var originUrl = window.location.origin;
        	
        	if(locationUrl != null ){
        		 window.location.href= "http://" + locationUrl.host + "?_t="+new Date().getTime();
        		// top.location.href = locationUrl.host;	
        		//window.location.replace(locationUrl.host) ;
        		//window.location.assign("http://" + locationUrl.host);
        		return ;
        	}
        	if(originUrl != null){
       		
        		window.location.href ="http://"  + originUrl.host + "?_t="+new Date().getTime();
        		//top.location.href = originUrl.host;
        		//window.location.replace(originUrl.host) ;
        		//window.location.assign("http://" + originUrl.host);

        		return;
        	}
        } else {
            $("#authCode").val('');
            $("#tipMsg").text(data.reason);
        }
    }
</script>
<div class="modal fade" id="authModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">短信验证</h4>
            </div>
            <div class="modal-body">
                <table class="table table-bordered table-hover" style="width: 100%">
                    <tbody>
                    <tr><td class="col-lg-2">预留手机号</td>
                        <td>
                            ${mobile}
                            <input type="hidden" id="needAuth" value="${needAuth}"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="col-lg-2">短信验证码</td>
                        <td class="col-lg-3">
                            <table>
                                <tr>
                                    <td><input type="text" name="authCode" id="authCode" class="form-control"/>
                                    </td>
                                    <td><button type="button" class="btn btn-primary" id="sendCode" onclick="sendCode()">获取验证码</button></td>
                                </tr>
                                <tr>
                                    <td colspan="2"><label id="tipMsg" style="color: red;"></label></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn  btn-primary" onclick="submitCode()">提交</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="commonModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 400px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="commonModalTitle">提示框</h4>
            </div>
            <div class="modal-body" id="commonModalBody">

            </div>
            <div class="modal-footer" id="commonModalFoot">
                <button type="button" class="btn  btn-primary" id="commonModalConfirm" >确认</button>
            </div>
        </div>
    </div>
</div>

<div id="loading_cover" class="cover">
    <img src="/static/images/loading.gif">
</div>