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
    <el-drawer :title="drawerTitle" :visible.sync="drawerOpen" direction="rtl" size="90%">
      <div class="app-container">
        <el-form ref="form" :model="form" :rules="rules" label-width="100px">
          <el-row :gutter="10">
            <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
              <el-form-item label="上级菜单" size="small" prop="parentId" v-if="true">
                <treeselect v-model="form.parentId" :options="menuOptions" :normalizer="normalizer" :show-count="true" placeholder="选择上级菜单"/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
              <el-form-item label="菜单类型" size="small" prop="funcType" v-if="true">
                <el-radio-group v-model="form.funcType">
                  <el-radio-button label="m">菜单</el-radio-button>
                  <el-radio-button label="f">功能</el-radio-button>
                  <el-radio-button label="c">按钮</el-radio-button>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="12" :lg="12" :xl="12">
              <el-form-item label="功能名称" size="small" prop="funcName" v-if="true">
                <el-input v-model="form.funcName" placeholder="功能名称"/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="12" :lg="12" :xl="12">
              <el-form-item label="排序顺序" size="small" prop="orderNum" v-if="true">
                <el-input-number v-model="form.orderNum" type="number" controls-position="right" placeholder="排序顺序"/>
              </el-form-item>
            </el-col>
            <el-col :xs="0" :sm="24" :md="24" :lg="24" :xl="24">
              <el-form-item label="图标" size="small" prop="icon" v-if="true">
                <el-popover placement="bottom-start" :width="490" trigger="click" @show="$refs['iconSelect'].reset()">
                  <IconSelect ref="iconSelect" @selected="selected" />
                  <el-input slot="reference" v-model="form.icon" placeholder="点击选择图标" readonly>
                    <svg-icon v-if="form.icon" slot="prefix" :icon-class="form.icon" class="el-input__icon" style="height: 32px; width: 16px"/>
                    <i v-else slot="prefix" class="el-icon-search el-input__icon" />
                  </el-input>
                </el-popover>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="12" :lg="12" :xl="12">
              <el-form-item label="是否外链" size="small" prop="isFrame" v-if="true">
                <span slot="label">
                  <el-tooltip content="选择是外链则路由地址需要以`http(s)://`开头" placement="top">
                    <i class="el-icon-question"/>
                  </el-tooltip>
                  是否外链
                </span>
                <el-radio-group v-model="form.isFrame">
                  <el-radio-button label="0">是</el-radio-button>
                  <el-radio-button label="1">否</el-radio-button>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="12" :lg="12" :xl="12">
              <el-form-item label="请求地址" size="small" prop="funcUrl" v-if="true">
                <span slot="label">
                  <el-tooltip content="访问的路由地址，如：`user`，如外网地址需内链访问则以`http(s)://`开头" placement="top">
                    <i class="el-icon-question"/>
                  </el-tooltip>
                  请求地址
                </span>
                <el-input v-model="form.funcUrl" placeholder="请求地址"/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
              <el-form-item label="前端组件" size="small" prop="component" v-if="true">
                <span slot="label">
                  <el-tooltip content="访问的组件路径，如：`system/user/index`，默认在`views`目录下" placement="top">
                    <i class="el-icon-question"/>
                  </el-tooltip>
                  前端组件
                </span>
                <el-input v-model="form.component" placeholder="前端组件"/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="12" :lg="12" :xl="12">
              <el-form-item label="权限字符" size="small" prop="funPerms" v-if="true">
                <span slot="label">
                  <el-tooltip content="控制器中定义的权限字符，如：@PreAuthorize(`@ss.hasPermi('system:user:list')`)" placement="top">
                    <i class="el-icon-question"/>
                  </el-tooltip>
                  权限字符
                </span>
                <el-input v-model="form.funPerms" placeholder="权限字符"/>
              </el-form-item>
            </el-col>
            <!-- <el-col :xs="24" :sm="12" :md="12" :lg="12" :xl="12">
              <el-form-item label="路由参数" size="small" prop="component" v-if="true">
                <span slot="label">
                  <el-tooltip content='访问路由的默认传递参数，如：`{"id": 1, "name": "demo"}`' placement="top">
                    <i class="el-icon-question"/>
                  </el-tooltip>
                  路由参数
                </span>
                <el-input v-model="form.component" placeholder="路由参数"/>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="12" :lg="12" :xl="12" v-if="true">
              <el-form-item label="是否缓存" size="small" prop="visible">
                <span slot="label">
                  <el-tooltip content="选择是则会被`keep-alive`缓存，需要匹配组件的`name`和地址保持一致" placement="top">
                    <i class="el-icon-question"/>
                  </el-tooltip>
                  是否缓存
                </span>
                <el-radio-group v-model="form.visible">
                  <el-radio-button label="0">缓存</el-radio-button>
                  <el-radio-button label="1">不缓存</el-radio-button>
                </el-radio-group>
              </el-form-item>
            </el-col> -->
            <el-col :xs="24" :sm="12" :md="12" :lg="12" :xl="12">
              <el-form-item label="功能显示状态" size="small" prop="visible" v-if="true">
                <span slot="label">
                  <el-tooltip content="选择隐藏则路由将不会出现在侧边栏，但仍然可以访问" placement="top">
                    <i class="el-icon-question"/>
                  </el-tooltip>
                  显示状态
                </span>
                <el-radio-group v-model="form.visible">
                  <el-radio-button label="0">显示</el-radio-button>
                  <el-radio-button label="1">隐藏</el-radio-button>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="12" :md="12" :lg="12" :xl="12">
              <el-form-item label="状态" size="small" prop="status" v-if="true">
                <span slot="label">
                  <el-tooltip content="选择停用则路由将不会出现在侧边栏，也不能被访问" placement="top">
                    <i class="el-icon-question"/>
                  </el-tooltip>
                  启用状态
                </span>
                <el-radio-group v-model="form.status">
                  <el-radio-button label="0">正常</el-radio-button>
                  <el-radio-button label="1">停用</el-radio-button>
                </el-radio-group>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
      <div class="form-footer">
        <el-button type="primary" :loading="loading" @click="submitForm">{{ loading ? "提交中 ..." : "确 定" }}</el-button>
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
      drawerOpen: true,
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