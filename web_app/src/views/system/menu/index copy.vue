<template>
  <div class="app-container">
    <!-- 表头搜索 -->
    <el-form v-show="showsearch" ref="queryForm" :model="queryParams" :inline="true" label-width="68px" >
      <el-form-item label="用户账号" prop="userName">
        <el-input v-model="queryParams.userName" placeholder="参数1" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="参数2" prop="ipaddr">
        <el-input v-model="queryParams.ipaddr" placeholder="请输入参数2" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!--数据表体-->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleDrawerOpen">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini">导出</el-button>
      </el-col>
      <right-toolbar :show-search.sync="showsearch" @queryTable="getList" />
    </el-row>

    <!-- 表格 -->
    <el-table v-loading="loading" :data="logininforList" row-key="funcPk" :tree-props="{ children: 'children', hasChildren: 'hasChildren' }" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column prop="funcName" align="center" label="功能名称" />
      <el-table-column prop="funPerms" align="center" label="功能权限编码" />
      <el-table-column prop="orderNum" align="center" label="排序" />
      <el-table-column prop="icon" align="center" label="图标">
        <template slot-scope="scope">
          <svg-icon :icon-class="scope.row.icon" />
        </template>
      </el-table-column>
      <el-table-column prop="funcType" align="center" label="功能类型" />
      <el-table-column prop="component" align="center" label="前端组件" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template>
          <el-button size="mini" type="text" icon="el-icon-edit">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--分页-->
    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
    />
    
    <!-- 添加修改页面 -->
    <el-drawer
      :title="drawerTitle"
      :visible.sync="drawerOpen"
      direction="rtl"
      size="80%"
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-input v-if="false" v-model="form.funcPk" />
        <el-form-item label="父节点" prop="parentId">
          <treeselect
            v-model="form.parentId"
            :options="menuOptions"
            :normalizer="normalizer"
            :show-count="true"
            placeholder="选择上级菜单"
          />
        </el-form-item>
        <el-form-item label="功能名称" prop="funcName">
          <el-input
            v-model="form.funcName"
            size="small"
            placeholder="功能名称"
          />
        </el-form-item>
        <el-form-item label="请求地址" prop="funcUrl">
          <el-input
            v-model="form.funcUrl"
            size="small"
            placeholder="请求地址"
          />
        </el-form-item>
        <el-form-item label="功能权限编码" prop="funPerms">
          <el-input
            v-model="form.funPerms"
            size="small"
            placeholder="功能权限编码"
          />
        </el-form-item>
        <el-form-item label="功能显示状态" prop="visible">
          <span slot="label">
            <el-tooltip
              content="选择隐藏则路由将不会出现在侧边栏，但仍然可以访问"
              placement="top"
              ><i class="el-icon-question"
            /></el-tooltip>
            功能显示状态
          </span>
          <el-radio-group v-model="form.visible">
            <el-radio label="0">是</el-radio>
            <el-radio label="1">否</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="排序" prop="orderNum">
          <el-input
            v-model="form.orderNum"
            size="small"
            type="number"
            placeholder="排序"
          />
        </el-form-item>
        <el-form-item label="功能说明" prop="funcExplanation">
          <el-input v-model="form.funcExplanation" placeholder="功能说明" />
        </el-form-item>
        <el-form-item label="前端组件" prop="component">
          <el-input
            v-model="form.component"
            size="small"
            placeholder="前端组件"
          />
        </el-form-item>
        <el-form-item label="功能类型" prop="funcType">
          <span slot="label">
            <el-tooltip content="功能类型" placement="top"
              ><i class="el-icon-question"
            /></el-tooltip>
            功能类型
          </span>
          <el-radio-group v-model="form.funcType">
            <el-radio label="0">是</el-radio>
            <el-radio label="1">否</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="图标">
          <el-popover
            placement="bottom-start"
            width="460"
            trigger="click"
            @show="$refs['iconSelect'].reset()"
          >
            <IconSelect ref="iconSelect" @selected="selected" />
            <el-input
              slot="reference"
              v-model="form.icon"
              placeholder="点击选择图标"
              readonly
            >
              <svg-icon
                v-if="form.icon"
                slot="prefix"
                :icon-class="form.icon"
                class="el-input__icon"
                style="height: 32px; width: 16px"
              />
              <i v-else slot="prefix" class="el-icon-search el-input__icon" />
            </el-input>
          </el-popover>
        </el-form-item>
        <el-form-item label="是否外链" prop="isFrame">
          <span slot="label">
            <el-tooltip
              content="选择是外链则路由地址需要以`http(s)://`开头"
              placement="top"
              ><i class="el-icon-question"
            /></el-tooltip>
            是否外链
          </span>
          <el-radio-group v-model="form.isFrame">
            <el-radio label="0">是</el-radio>
            <el-radio label="1">否</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <span slot="label">
            <el-tooltip
              content="选择停用则路由将不会出现在侧边栏，也不能被访问"
              placement="top"
              ><i class="el-icon-question"
            /></el-tooltip>
            启用状态
          </span>
          <el-radio-group v-model="form.status">
            <el-radio label="0">是</el-radio>
            <el-radio label="1">否</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div>
        <el-button type="primary" :loading="loading" @click="submitForm">{{
          loading ? "提交中 ..." : "确 定"
        }}</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import { getListFunc, saveFunc, listMenu } from "@/api/func";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import IconSelect from "@/components/IconSelect";
export default {
  components: { Treeselect, IconSelect },
  data() {
    return {
      // 显示搜索条件
      showsearch: true,
      // 显示表单
      drawerOpen: false,
      // 遮罩层
      loading: true,
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 系统访问记录表格数据
      logininforList: [],
      // 添加修改页面title
      drawerTitle: null,
      // 父节点
      menuOptions: [],
      timer: null,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
      form: {},
      rules: {},
    };
  },
  created() {
    this.getList();
  },
  methods: {
    // 选择图标
    selected(name) {
      this.form.icon = name;
      this.$forceUpdate(); // 强制刷新结果，不然无法正常显示
    },
    /** 转换菜单数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.funcPk,
        label: node.funcName,
        children: node.children,
      };
    },
    /** 查询菜单下拉树结构 */
    getTreeselect() {
      listMenu(this.queryParams).then((response) => {
        this.menuOptions = [];
        const menu = { funcPk: "0", funcName: "主类目", children: [] };
        menu.children = this.handleTree(response.data, "funcPk");
        this.menuOptions.push(menu);
      });
    },
    // 获取列表
    getList() {
      this.loading = true;
      getListFunc(this.queryParams).then((result) => {
        this.logininforList = result.rows;
        this.total = result.total;
        this.loading = false;
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.queryParams = {
        pageNum: 1,
        pageSize: 10,
        userName: null,
        ipaddr: null,
        loginLocation: null,
        browser: null,
        os: null,
        status: null,
        msg: null,
        loginTime: null,
      };
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.infoId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    handleDrawerOpen() {
      const from = this.form;
      this.getTreeselect();
      if (from.funcPk == null) {
        this.drawerTitle = "新增功能";
      } else {
        this.drawerTitle = "修改功能";
      }
      this.drawerOpen = true;
    },
    submitForm() {
      if (this.loading) {
        return;
      }
      this.$confirm("确定要提交表单吗？")
        .then((_) => {
          this.loading = true;
          this.timer = setTimeout(() => {
            saveFunc(this.form)
              .then((request) => {
                console.log(request);
                this.loading = false;
                this.cancel();
              })
              .catch((err) => {
                this.loading = false;
                console.log(err.response);
              });
            // 动画关闭需要一定的时间
            setTimeout(() => {}, 40);
          }, 2000);
        })
        .catch((_) => {});
    },
    cancel() {
      this.drawerOpen = false;
      clearTimeout(this.timer);
    },
  },
};
</script>

<style rel="stylesheet/scss" lang="scss">
.top-right-btn {
  position: relative;
  float: right;
}
.el-drawer {
  overflow: scroll;
}
</style>
