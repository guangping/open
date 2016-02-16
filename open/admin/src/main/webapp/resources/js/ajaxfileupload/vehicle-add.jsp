<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<%@taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@taglib prefix="fn" uri="/WEB-INF/tld/fn.tld" %>
<script type ="text/javascript" src =" ${ctx} /resource/bootstrap/plugins/ajaxfileupload/ajaxfileupload.js" ></script >
<form method="post" id="searchForm" onsubmit="return false;" action="#">
   <input type="hidden" name="id" value="${csrUser.id}">
   <input type="hidden" name="vehicle_pic1" id="hvehicle_pic1" value="">
   <input type="hidden" name="vehicle_pic2" id="hvehicle_pic2" value="">

    <div class="modal-dialog modal-wide ">   
        <div class="modal-content">
        
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">添加车辆类型</h4>
            </div>
            
            <div class="modal-body">
                <div class="form-body">
                    <span id="msg" class="help-block red" style="display: none;color: red"></span>
                </div>
                
                <div class="row">
                    <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label">车型名称</label>

                           <input class="form-control" name="vehicle_name" value="" type="text">
                        </div>
                    </div>
                    <div class="col-md-6">
                    <div class="form-group">
                        <label class=" control-label">所属地区</label>
                                <select name="city_code" id="city_code" class="form-control">
			                           <c:forEach var="city" items="${city }">
			                                    <option value="${city.city_code}">${city.city_name }</option>
			                           </c:forEach>
                                </select>
                        </div>
                    </div>
                </div> 
             
                 <div class="row">
                     <div class="col-md-6">
                     <div class="form-group">
                        <label class="control-label">载重</label>
                            <input class="form-control" name="weight" value="" type="text">
                        </div>
                        <!--<label class="col-md-2 control-label">单位(吨)</label> -->
                     </div>
                     <div class="col-md-6">
                        <div class="form-group">
                        <label class="control-label">货箱长</label>
                            <input class="form-control" name="length" value="" type="text">
                        </div>
                     </div>
                </div>
                
                 <div class="row">
                   <div class="col-md-6">
                        <div class="form-group">
                        <label class="control-label">货箱宽</label>
                            <input class="form-control" name="width" value=""  type="text">
                        </div>
                    </div>
                    <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label">货箱高</label>
                            <input class="form-control" name="high" value=""  type="text">
                        </div>
                    </div>
                </div>
                
                <div class="row">
                     <div class="col-md-6">
                     <div class="form-group">
                        <label class="control-label">起步价</label>
                            <input class="form-control" name="starting_price" value="" type="text">
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group">
                        <label class="control-label">每公里价格</label>
                            <input class="form-control" name="unit_price" value=""   type="text">
                        </div>
                     </div>
                 </div>
                 <div class="row">
                     <div class="col-md-6">
                     <div class="form-group">
                        <label class="control-label">起步公里数</label>
                            <input class="form-control" name="starting_unit" value="" type="text">
                        </div>
                      </div>
                      
                       <div class="col-md-6">
                         <div class="form-group">
                        <label class="control-label">排序</label>
                            <input class="form-control" name="sort" value="${vehicle.sort}"   type="text">
                        </div>
                    </div>
                 </div>
                
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                        <label class="control-label">加急价格</label>
                            <input class="form-control" name="dispatch_price" value=""  type="text">
                        </div>
                     </div>
                    <div class="col-md-6">
                        <div class="form-group">
                        <label class="control-label">跟车价格</label>
                            <input class="form-control" name="with_car"   value=""  type="text">
                        </div>
                    </div>
                 </div>
                 
                <div class="row">
                   <div class="col-md-6">
                          <div class="form-group">
                            <label class="control-label">围栏里程数(米)</label>
                            <input class="form-control" name="weilan" value=""   type="text">
                          </div>
                     </div>
                     
                      <div class="col-md-6">
                          <div class="form-group">
                            <label class="control-label">包时模式(起步价)</label>
                            <input class="form-control" name="time_starting_price" value=""   type="text">
                          </div>
                     </div>
                </div>
                   
                
                <div class="row">
                   <div class="col-md-6">
                          <div class="form-group">
                            <label class="control-label">包时模式(超出x分钟单价)</label>
                            <input class="form-control" name="time_min_price" value=""   type="text">
                          </div>
                     </div>
                   <div class="col-md-6">
                          <div class="form-group">
                            <label class="control-label">超出x分钟</label>
                            <input class="form-control" name="time_out_min" value=""   type="text">
                          </div>
                     </div>
                </div>
                
                
                 <div class="row">
                     <div class="col-md-6">
                          <div class="form-group">
                            <label class="control-label">包时模式(起步分钟数)</label>
                            <input class="form-control" name="time_starting_hour" value=""   type="text">
                          </div>
                     </div>
                     
                    
                     
                      <div class="col-md-6">
                      <div class="form-group">
                            <label class="control-label">推送范围</label>
                            <input class="form-control" name="push_mileage" value="${vehicle.push_mileage }"   type="text">
                      </div>
                      </div>
                </div>
                
      
                
                <div class="row">
                
                
                      <div class="col-md-6">
                         <div class="form-group">
                        <label class="control-label">车型图片</label>
                         <div class="margin-top-10 fileupload file-upload-new" data-provides="fileupload">
                            <input class="default" name="fileName"  id="vehicle_pic1" onchange="ajaxfileUpload('vehicle_pic1')"  type="file">
                        </div>
                       </div>
                   </div>
                  
                   
                   
                  <div class="col-md-6">
                       <div class="form-group">
                        <label class="control-label">车型图片2</label>
                         <div class="margin-top-10 fileupload file-upload-new" data-provides="fileupload">
                            <input class="default" name="fileName"  id="vehicle_pic2" onchange="ajaxfileUpload('vehicle_pic2')"  type="file">
                        </div>
                       </div>
                   </div>
                </div>
                
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm" data-dismiss="modal">关闭</button>
                <button type="button" id="save" class="btn  btn-sm blue">保存</button>
            </div>
        </div>
    </div>
</form>
<script type="text/javascript">
    var GradeAddValidator = function () {
        var handleSubmit = function () {
            $('#searchForm').validate({
                errorElement: 'span',
                errorClass: 'help-block',
                focusInvalid: false,
                rules: {
                	with_car: {required: true,checkInterger:true},
                	weight: {range: [0, 15],checkInterger:true},
                	length: {range: [0, 15],checkInterger:true},
                	high: {range: [0, 15],checkInterger:true},
                	width: {range: [0, 15],checkInterger:true},
                    dispatch_price: {required: true,checkInterger:true},
                    unit_price: {required: true,checkInterger:true},
                    starting_unit:{required: true,checkInterger:true},
                    starting_price: {required: true,checkInterger:true},
                    vehicle_name: {required: true},
                    sort: {required:true,range: [0, 100],checkInterger:true},
                    weilan: {range: [2000, 10000]},
                    time_starting_price: {required: true},
                    time_starting_hour: {required: true},
                    time_min_price: {required: true},
                    time_out_min: {required: true},
                    push_mileage:{range:[3,99],checkInterger:true}
                },
                messages: {
                	with_car: {required: "跟车价格不能为空!"},
                	weight: {range: "所需要求介于 {0} 和 {15} 之间!"},
                	length: {range: "所需要求介于 {0} 和 {15} 之间!"},
                	high: {range: "所需要求介于 {0} 和 {15} 之间!"},
                	width: {range: "所需要求介于 {0} 和 {15} 之间!"},
                	dispatch_price: {required: "加急价格不能为空!"},
                	unit_price: {required: "每公里价格不能为空!"},
                	starting_unit:{required:"起步公里数不能为空!"},
                	starting_price: {required: "起步价不能为空!"},
                	vehicle_name: {required: "车型名称不能为空!"},
                    sort: {required: "不能为空!",range: "所需要求介于 {0} 和 {100} 之间!"},
                    weilan: {range: "围栏里程数不能为空(2000-10000米)!"},
                    time_starting_price: {required: "包时模式，起步价不能为空!",checkInterger:true},
                    time_starting_hour: {required: "包时模式，起步分钟数不能为空!",checkInterger:true},
                    time_min_price: {required: "包时模式，超出x分钟单价不能为空!",checkInterger:true},
                    time_out_min: {required: "超出x分钟不能为空!",checkInterger:true},
                    push_mileage:{range:"推送范围介于 {3} 和 {99} 之间！"}
                },
                highlight: function (element) {
                    $(element).closest('.form-group').addClass('has-error');
                },
                success: function (label) {
                    label.closest('.form-group').removeClass('has-error');
                    label.remove();
                },
                errorPlacement: function (error, element) {
                    element.parent('div').append(error);
                },
                submitHandler: function (form) {
                    var params = ServiceUtils.getInputDomain('searchForm');
                    Service.execute('${ctx}/admin/bus/vehicle/add.html', params, function (cb) {
                        if (cb.success) {
                            closeModal();
                            bootbox.alert('操作成功!');
                            $('#searchvehicle').trigger('click');
                        } else {
                            $('#msg').show().html('操作失败!' + cb.msg);
                        }
                    },'post')
                }
            });
            $('.form-horizontal input').keypress(function (e) {
                if (e.which == 13) {
                    if ($('#searchForm').validate().form()) {
                        $('#searchForm').submit();
                    }
                    return false;
                }
            });
        }
        return {
            init: function () {
                handleSubmit();
                $('#save').on('click', function () {
                    if ($('#searchForm').validate().form()) {
                        $('#searchForm').submit();
                    }
                });
            }
        };
    }();

</script>
<script type="text/javascript">
function ajaxfileUpload(typevalue){
	   $("#loading_pic").ajaxStart(function (){
		   $(this).show();
	   }).ajaxComplete(function(){
		   $(this).hide();
	   });
	   $.ajaxFileUpload({
		   url:'${ctx}/upload/face_user/yun.html',
		   secureuri:false,
		   fileElementId:typevalue,
		   dataType:'json',
		   success:function(data,status){
			   
			   if(data.success){
				   //$('#face_img').attr('src',data.url);
				   $('#logo').val(data);
				   $("#h"+typevalue+"").val(data.url);
			   }
		   },
		   error:function(data,status,e){
			   alert(e);
		   }
		   
	   })
	   return false;
  }

    $(function () {
   
    	  GradeAddValidator.init();
 
    })
</script>

