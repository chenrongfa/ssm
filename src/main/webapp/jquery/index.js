/**
 * 对员工查询数据的dom操作
 */
/*
 * 当前页
 */
var current;
/**
 * 总页
 */
var pages;
/**
 * 验证表单信息 3 通过 0 1 2错误
 */
var form_message;

var date = new Date();
$(function() {

	/**
	 * 延迟加载 当执行函数玩时执行done里面的方法
	 */
	$.get(pagesTo(1)).done(function() {
		select_byDelete_All();
	});

	$(document).on("click", ".delete_byId", function() {
		deleteById(this);
	});

	$(document).on("click", ".delete_checked", function() {
		delete_All_bySelectcb();
	});

	$("#delete_Allbtn").click(function() {
		delete_byCheckedBox();
	});
	$("#delete_All").click(function() {
		select_byDelete_All();
	});
	/**
	 * 添加事件
	 */
	$("#add_emp").click(function() {

		add_emp();

	});
	/**
	 * 保存 按键点击事件
	 */
	$("#save").click(function() {
		validate_form();
		alert(form_message);
		if (form_message == 3) {
			put_employy();
		} else {
			alert("请检查数据格式");
		}

	});
	$(":input ").change(function() {

		var flag = $(this).prop("id");
		var value = $(this).prop("value");

		if (value == "") {
			$(this).parents(".form-group").find("span:first").empty();
		} else {
           /**
            *   增加员工表单验证
            */
			if (flag == "empName") {
				var pattern = /^[a-z0-9_-]*[\u2E80-\u9FFF]{2,16}$/;
				validate(flag, "empName_help", pattern, "格式错误:必须以字母开头两个汉字");
			} else if (flag == "empEmail") {
				var patternEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
				validate(flag, "empEmail_help", patternEmail, "格式错误:以字母开头@");
			} else if ("empBirthday" == flag) {
				var pattern=/[\d]{4}-[\d]{2}-[\d]{2}/;
				validate(flag, "empBirthday_help", pattern, "格式如下yyyy-mm-dd");
			}
			/**
			 *   更新员工表单验证
			 */
			
			else if ("empName_update" == flag) {
				alert("1");
				var pattern = /^[a-z0-9_-]*[\u2E80-\u9FFF]{2,16}$/;
				validate(flag, "empName_message", pattern, "格式错误:必须以字母开头两个汉字");
			}else if ("empEmail_update" == flag) {
				alert("1");
				var patternEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
				validate(flag, "empEmail_message", patternEmail, "格式错误:必须以字母开头两个汉字");
			}else if ("empBirthday_update" == flag) {
				alert("1");
				var pattern=/[\d]{4}-[\d]{2}-[\d]{2}/;
				validate(flag, "empBirthday_message", pattern, "格式错误:yyy-mm-dd");
			}

		}
		

		/**
		 * 重置
		 */
		form_message = 0;
	});
	/**
	 * 更改按钮绑定点击事件
	 */
	$(document).on("click", ".update_byId", function() {
		
		update_byEmpId(this);
	});
	/**
	 *  更新员工按钮事件
	 */
	$("#update").click(function(){
		$("span.help-block").empty();
		update_emp();
	});
});
function pagesTo(result) {
	current = result;
	$.ajax({
		type : "get",
		url : "emp-list",
		data : "page=" + result,
		success : function(data) {
			pages = data.dataBean.pageInfo.pages;
			current = data.dataBean.pageInfo.pageNum;

			buid_emp_table(data);
			buid_emp_pagesRecord(data);
			buid_emp_nav(data);

		}
	});
};
/*
 * 填充表数据
 */
function buid_emp_table(result) {
	$(".delete").empty();
	$.each(result.dataBean.pageInfo.list, function(index, emp) {
		date.setTime(emp.empBirthday);
		var checkbox = $("<input/>").addClass("delete_checked").attr("type",
				"checkbox").attr("value", emp.empId);
		var emp_id = $("<th></th>").append(emp.empId);
		var emp_1 = $("<th></th>").append(checkbox);
		var emp_name = $("<th></th>").append(emp.empName);
		var emp_email = $("<th></th>").append(emp.empEmail);
		if (emp.empBirthday > 0) {

			var emp_birthday = $("<th></th>").append(date.toDateString());
		} else {
			var emp_birthday = $("<th></th>").append("");
		}
		var emp_deptName = $("<th></th>").append(emp.dept.deptName);
		var edit_span = $("<span></span>").addClass(
				"glyphicon glyphicon-pencil").attr("aria-hidden", true);
		var delete_span = $("<span></span>").addClass(
				"glyphicon glyphicon-remove").attr("aria-hidden", true);
		var edit_button = $("<button></button>").addClass(
				"btn btn-success btn-sm update_byId").append(edit_span).append(
				"更改").css({
			"margin-right" : "5px"
		});
		var delete_button = $("<button></button>").addClass(
				"btn btn-success btn-sm delete_byId").attr("delete_id",
				emp.empId).attr("delete_name", emp.empName).append(delete_span)
				.append("删除");

		var edit = $("<div></div>").addClass("center").append(edit_button)
				.append(delete_button);
		var edit_column = $("<th></th>").addClass("center").append(edit);

		$("<tr></tr>").append(emp_1).append(emp_id).append(emp_name).append(
				emp_email).append(emp_birthday).append(emp_deptName).append(
				edit_column).addClass("delete").appendTo($("#table_emp"));

	});

}

/*
 * 
 * 记录页数
 * 
 */
function buid_emp_pagesRecord(result) {
	$("#emp_pagesRecord ").empty();
	var currentPage = result.dataBean.pageInfo;
	$("<span></span>").addClass("div-center").append(
			"当前页数:" + currentPage.pageNum + "   总" + currentPage.pages
					+ "页 总记录" + currentPage.total + "条").appendTo(
			$("#emp_pagesRecord"));

}

/*
 * 建立分页导航
 */
function buid_emp_nav(result) {
	$("#emp_nav").empty();
	var pageInfo = result.dataBean.pageInfo;
	var ul = $("<ul></ul>").addClass("pagination");
	var index = $("<a></a>").attr("href", "#").append("首页");
	var index_li = $("<li></li>").append(index);
	ul.append(index_li);
	index_li.click(function() {
		pagesTo(1);
	});
	var pre = $("<a></a>").attr("href", "#").attr("aria-label", "Previous")
			.append($("<span></span>")).attr("aria-hidden", true).append(
					"&laquo;");
	var pre_li = $("<li></li>").append(pre);
	ul.append(pre_li);
	if (pageInfo.hasPreviousPage) {
		pre_li.click(function() {
			pagesTo(pageInfo.pageNum - 1);
		});

	} else {
		pre_li.addClass("disabled");
	}

	$.each(pageInfo.navigatepageNums, function(index, page) {

		var page_a = $("<a></a>").attr("href", "#").append(page);
		var middle_li = $("<li></li>").append(page_a);
		if (pageInfo.pageNum == page) {
			middle_li.addClass("active");
		}
		ul.append(middle_li);
		middle_li.click(function() {
			pagesTo(page);

		});
	});

	var next = $("<a></a>").attr("href", "#").attr("aria-label", "next")
			.append($("<span></span>")).attr("aria-hidden", true).append(
					"&raquo;");
	var next_li = $("<li></li>").append(next);
	ul.append(next_li);
	if (pageInfo.hasNextPage) {
		next_li.click(function() {
			pagesTo(pageInfo.pageNum + 1);
		}

		);

	} else {
		next_li.addClass("disabled");
	}

	var tail = $("<a></a>").attr("href", "#").append("尾页");
	var tail_li = $("<li></li>").append(tail);
	ul.append(tail_li);
	tail_li.click(function() {
		pagesTo(pageInfo.pages);
	});

	ul.appendTo($("#emp_nav"));
}
/**
 * 通过id删除员工
 * 
 * @param object
 * @returns {Boolean}
 */
function deleteById(object) {
	if (confirm("是否要删除" + ($(object).attr("delete_name")))) {
		var send = "empId=" + $(object).attr("delete_id") + "&_method=DELETE";
		$.ajax({
			url : "emp",
			type : "POST",
			data : send,
			success : function(data) {
				console.log(data);
				if (data.code == 200) {
					pagesTo(current);
				} else {
					alert("删除错误");
				}
			}
		});

	}
	return false;
}
/**
 * 删除选中的员工
 */
function delete_byCheckedBox() {
	var checkedLength = $(".delete_checked:checked").length;
	var totalLength = $(".delete_checked").length;
	if (checkedLength > 0) {
		var emp_ids = new Array();
		for (var i = 0; i < checkedLength; i++) {
			emp_ids[i] = $(".delete_checked:checked").eq(i).val();
		}
		var emp_id = emp_ids.toString();
		if (checkedLength > 5) {
			emp_ids = emp_ids.slice(0, 5);
			emp_ids[4] = "等等";
		}
		var emp_id_show = "emp_id=" + emp_ids.toString();
		if (confirm("是否删除" + emp_id_show)) {
			$.ajax({
				url : "emp-select",
				type : "post",
				contentType : "text/plain;charset=utf-8",
				data : emp_id,
				success : function(data) {
					console.log(data);
					if (data.code == 200) {

						if (checkedLength >= totalLength) {
							pagesTo(current - 1);
						} else {

							pagesTo(current);
						}
					}
				}
			});
		}
	} else {
		alert("请选择删除的员工");
	}

}
/**
 * 通过Delete_All 选中其他的复选框..
 * 
 */
function select_byDelete_All() {

	if ($("#delete_All").prop("checked")) {
		$(".delete_checked").prop("checked", true);
	} else {
		$(".delete_checked").prop("checked", false);

		alert(2);
	}

}
/**
 * 通过全选选中其他
 */
function delete_All_bySelectcb() {
	var checkedLength = $(".delete_checked:checked").length;
	var totalLength = $(".delete_checked").length;
	if (checkedLength == totalLength) {
		$("#delete_All").prop("checked", true);
	} else {
		$("#delete_All").prop("checked", false);
	}
}
/**
 * 启动模态框
 */
function add_emp() {
	/**
	 * 发送ajax 获取到部门信息
	 * 
	 * 
	 */
	$("span.help-block").empty();
	$.ajax({
		url : "dept-all",
		type : "get",
		success : function(data) {
			$(":input").val("");
			if (data.code == 200) {
				$("#addModal").modal('toggle');
				/*
				 * 解析数据
				 */
				console.log(data);

				build_dept_select(data);
			}
		}
	});

}
/**
 * 创建部门下拉框
 * 
 * @param result
 * @returns
 */
function build_dept_select(result, id) {
	$("#deptSelect").empty();
	$.each(result.dataBean.dept, function(index, dm) {
		var option = $("<option></option>").attr("value", dm.deptId).append(
				dm.deptName);
		if (id != null) {
			$("#" + id).append(option);
		} else {
			$("#deptSelect").append(option);
		}
	});

}
function put_employy() {

	var emp_message = $("#add_form").serialize();

	$.ajax({
		url : "emp_add",
		type : "put",
		data : emp_message + "&_method=put",
		success : function(data) {
			if (data.code == 200) {
				if (!confirm("已经成功添加,是否继续")) {
					$("#addModal").modal('toggle');
					pagesTo(pages + 1);
				} else {
					/*
					 * 清空数据
					 */
					$("span.help-block").empty();
					$(".form-group :input").slice(0,
							$(".form-group :input").length - 1).val("");
					/* alert($(".form-group :input").length); */
				}

			} else {
				console.log(data);
				alert(data.dataBean.errors.isExist);
				if (data.dataBean.errors.isExist) {
					show_erro("empName_help", "用户存在");
				}
				if (data.dataBean.errors.empEmail != null) {
					show_erro("empEmail_help", data.dataBean.errors.empEmail);
					alert("添加失败");
				}
				if (data.dataBean.errors.empBirthday != null) {
					show_erro("empBirthday_help",
							data.dataBean.errors.empBirthday);
				}

			}
		}
	});
}
/**
 * 验证表单数据
 */
function validate_form() {
	form_message = 0;
	var pattern = /^[a-z0-9_-]*[\u2E80-\u9FFF]{2,16}$/;
	validate("empName", "empName_help", pattern, "格式错误:必须以字母开头两个汉字");

	var patternEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
	validate("empEmail", "empEmail_help", patternEmail, "格式错误:以字母开头@");

	var patternBirthday=/[\d]{4}-[\d]{2}-[\d]{2}/;
	validate("empBirthday", "empBirthday_help", patternBirthday, "格式如下yyyy-mm-dd");

}
/**
 *  抽取
 * @param inputName 表单id
 * @param spanId 
 * @param pattern  正则表达式
 */
function validate(inputName,spanId,pattern ,message) {
	var empName = $("#"+inputName).val();

	/* RegExp pattern=new RegExp("/^[a-z0-9_-]{3,16}$/","i"); */
	
	if (pattern.test(empName)) {

		form_message++;
		$("#"+spanId).parents(".form-group").find("span:first").empty();
	} else {
		form_message--;
		show_erro(spanId, message);
	}
}

/**
 *  添加错误信息
 * @param id
 * @param message
 */
function show_erro(id, message) {
	$("#" + id).empty().append(message);
}
function update_byEmpId(id) {
	$("span.help-block").empty();
	$.ajax({
		url : "dept-all",
		type : "get",
		success : function(data) {
			$(":input").val("");
			if (data.code == 200) {
				$("#updateModal").modal('toggle');
				/*
				 * 解析数据
				 */
				console.log(data);
				/**
				 *  填充模态框数据
				 */
				fill_updateModal(id);
				
				build_dept_select(data,"deptupdate");
			}else{
				
			}
		}
	});

}
function fill_updateModal(id){
	var $tr=$(id).parents("th").parents("tr");
	alert($tr.find("th:eq(4)").text());
	$("#emp_id").empty().append($tr.find("th:eq(1)").text());
	$("#empId_update").prop("value",$tr.find("th:eq(1)").text());
	$("#empName_update").prop("value",$tr.find("th:eq(2)").text());
	$("#empEmail_update").prop("value",$tr.find("th:eq(3)").text());
	$("#empBirthday_update").prop("value",$tr.find("th:eq(4)").text());
	
}
/**
 * 发送更新请求
 */
function update_emp(){
	
	var update=$("#update_form").serialize();
	$.ajax({
		url:"emp-update",
		type:"POST",
		data:update,
		success:function(data){
			/*if(data.dataBean.)*/
			console.log(data);
			var errors=data.dataBean.errors;
			if(data.code==200){
				if(!confirm("添加成功是否继续修改")){
					$("#updateModal").modal('toggle');
					pagesTo(current);
				}
			}
			if(errors!=null){
				/**
				 *  服务器验证
				 */
				build_error_updateform(errors);
			}
		}	
		
	});
	
}
/**
 *   更新错误信息填充
 * @param result
 */

function build_error_updateform(result){
	
	if(result.empName!=null){
		show_erro("empName_message", result.empName);
	}
	if(result.empEmail!=null){
		alert("进来1"+result.empEmail);
		show_erro("empEmail_message",result.empEmail);
	}
	if(result.empBirthday!=null){
		show_erro("empBirthday_message", result.empBirthday);
	}
	
	
}