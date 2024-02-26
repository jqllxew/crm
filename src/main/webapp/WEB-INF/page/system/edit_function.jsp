<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%--<%@ taglib prefix="s" uri="/struts-tags"%>--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">

    <title>编辑权限</title>

    <meta name="pragma" content="no-cache">
    <meta name="cache-control" content="no-cache">
    <meta name="expires" content="0">
    <%@include file="../script.html" %>
</head>

<body style="width:100%;max-width:600px;padding:30px 60px;">
<section class="info-section">
    <form id="form" method="post">
        <table>
            <tr>
                <td class="text-title">权限名称：</td>
                <td class="text-content">
                    <input type="hidden" name="id" value="${func.id }">
                    <input type="text" name="funcname" value="${func.funcname }"
                           class="easyui-textbox theme-textbox-radius"
                           data-options="required:true,validType:['length[2,20]','funcname']">
                </td>
            </tr>
            <tr>
                <td class="text-title">权限URL：</td>
                <td class="text-content">
                    <input type="text" name="funcurl" value="${func.funcurl }"
                           class="easyui-textbox theme-textbox-radius">
                </td>
            </tr>

            <tr>
                <td class="text-title">权限编码：</td>
                <td class="text-content">
                    <input type="text" name="funccode" value="${func.funccode }"
                           class="easyui-textbox theme-textbox-radius"
                           data-options="required:true,validType:['length[2,20]','funccode']">
                </td>
            </tr>
            <tr>
                <td class="text-title">权限类型：</td>
                <td class="text-content">
                    <select name="functype" class="easyui-combobox theme-textbox-radius">
                        <option value="1" ${func.functype==1 ? 'selected' : ''}>菜单</option>
                        <option value="0" ${func.functype==0 ? 'selected' : ''}>按钮</option>
                        <option value="" ${empty func.functype ? 'selected' : ''}>其它</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="text-title">上级权限：</td>
                <td class="text-content">
                    <input class="easyui-combotree" id="parent" value="${func.iconclass }">
                    <input type="hidden" name="parentid" value="${func.parentid }">
                </td>
            </tr>
            <tr>
                <td class="text-title">排序编号：</td>
                <td class="text-content">
                    <input type="text" name="sortnum" value="${func.sortnum }"
                           class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'sortnum'">
                </td>
            </tr>
            <tr>
                <td class="text-title">权限状态：</td>
                <td class="text-content">
                    <select name="status" class="easyui-combobox theme-textbox-radius">
                        <option value="1" ${func.status==1?"selected":"" }>正常</option>
                        <option value="0" ${func.status==0?"selected":"" }>禁用</option>
                        <option value="2" ${func.status==2?"selected":"" }>已删除</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="text-title">描述信息：</td>
                <td class="text-content">
                    <textarea class="theme-textbox-radius" rows="5" cols="20"
                              name="funcnote">${func.funcnote }</textarea></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center;margin-top: 10px;">
                    <a href="javascript:void(0);" id="saveBtn" class="easyui-linkbutton button-primary">保存</a>
                    <a href="javascript:void(0);" id="resetBtn" class="easyui-linkbutton button-danger">重置</a>
                </td>
            </tr>
        </table>
    </form>
</section>
<script type="text/javascript">

    $.extend($.fn.validatebox.defaults.rules, {
        funcname: {
            validator: function (value, param) {
                var status = false;
                $.ajax({
                    url: "system/function/FunctionAction_validate.do",
                    type: "get",
                    dataType: "json",
                    data: {"funcname": value, "funcid": '${func.id}'},
                    async: false,
                    success: function (result) {
                        if (result && result.success) {
                            status = result.success
                        }
                    }
                })
                return status;
            },
            message: '权限名已存在'
        },
        funccode: {
            validator: function (value, param) {
                var status = false;
                $.ajax({
                    url: "system/function/FunctionAction_validate.do",
                    type: "post",
                    dataType: "json",
                    data: {"funccode": value, "funcid": '${func.id}'},
                    async: false,
                    success: function (result) {
                        if (result && result.success) {
                            status = result.success
                        }
                    }
                })
                return status;
            },
            message: '权限编码已存在'
        },
        sortnum: {
            validator: function (value, param) {
                return /^[0-9]{0,}$/.test(value);
            },
            message: '请输入数字'
        }
    })

    $("#parent").combotree({
        url: "system/function/FunctionAction_findCombotree.action",
        method: "get",
        editable: true,
        loadFilter: function (data) {
            if (data && data.success) {
                return data.data;
            }
            return null;
        },
        onSelect: function (param) {
            $("input[name='parentid']").val(param.id)
        }
    });

    $("#saveBtn").on("click", function () {
        if (!$("#form").form('validate')) {
            return;
        }
        $.post("system/function/FunctionAction_edit.action",
            $("#form").serialize(), function (result) {
                if (result.ex) {
                    parent.$.messager.alert("警告", result.ex)
                    return;
                }
                if (result.success) {
                    parent.$.messager.alert("提示", result.message)
                    parent.closeTopWindow()
                    return;
                }
                $.messager.alert("警告", result.message)
            }, "json")
    })
</script>
</body>
</html>
				