<template>
  <div class="app-container">
    <!-- 表头搜索 -->
    <el-form v-show="showsearch" ref="queryForm" :model="queryParams" :inline="true" label-width="68px">
      <el-form-item label="用户账号" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="参数1"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="参数2" prop="ipaddr">
        <el-input
          v-model="queryParams.ipaddr"
          placeholder="请输入参数2"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <!--数据表体-->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleDrawerOpen"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
        >导出</el-button>
      </el-col>
      <right-toolbar :show-search.sync="showsearch" @queryTable="getList" />
    </el-row>
    <el-table
      v-loading="loading"
      :data="logininforList"
      row-key="funcPk"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column prop="funcName" align="center" label="功能名称" />
      <el-table-column prop="funPerms" align="center" label="功能权限编码" />
      <el-table-column prop="orderNum" align="center" label="排序" />
      <el-table-column prop="icon" align="center" label="图标" />
      <el-table-column prop="funcType" align="center" label="功能类型" />
      <el-table-column prop="component" align="center" label="前端组件" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--分页-->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
    />
    <!-- 添加修改页面 -->
    <el-drawer
      :title="drawerTitle"
      :visible.sync="table"
      direction="rtl"
      size="100%"
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-input v-if="false" v-model="form.funcPk" />
        <el-form-item label="功能名称" prop="funcName">
          <el-input v-model="form.funcName" size="small" placeholder="功能名称" />
        </el-form-item>
        <el-form-item label="父节点" prop="parentId">
          <el-input v-model="form.parentId" size="small" placeholder="父节点" />
        </el-form-item>
        <el-form-item label="请求地址" prop="funcUrl">
          <el-input v-model="form.funcUrl" size="small" placeholder="请求地址" />
        </el-form-item>
        <el-form-item label="功能权限编码" prop="funPerms">
          <el-input v-model="form.funPerms" size="small" placeholder="功能权限编码" />
        </el-form-item>
        <el-form-item label="功能显示状态" prop="visible">
          <el-input v-model="form.visible" size="small" placeholder="功能显示状态" />
        </el-form-item>
        <el-form-item label="排序" prop="orderNum">
          <el-input v-model="form.orderNum" size="small" type="number" placeholder="排序" />
        </el-form-item>
        <el-form-item label="功能说明" prop="funcExplanation">
          <el-input v-model="form.funcExplanation" placeholder="功能说明" />
        </el-form-item>
        <el-form-item label="前端组件" prop="component">
          <el-input v-model="form.component" size="small" placeholder="前端组件" />
        </el-form-item>
        <el-form-item label="功能类型" prop="funcType">
          <el-input v-model="form.funcType" size="small" placeholder="功能类型" />
        </el-form-item>
        <el-form-item label="图标" prop="icon">
          <el-input v-model="form.icon" size="small" placeholder="图标" />
        </el-form-item>
        <el-form-item label="是否外链" prop="isFrame">
          <el-input v-model="form.isFrame" size="small" placeholder="是否外链" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-input v-model="form.status" size="small" placeholder="状态" />
        </el-form-item>
      </el-form>
      <div>
        <el-button type="primary" :loading="loading" @click="submitForm">{{ loading ? '提交中 ...' : '确 定' }}</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import { getListFunc } from '@/api/func'
export default {
  data() {
    return {
      // 显示搜索条件
      showsearch: true,
      // 显示表单
      table: false,
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
      timer: null,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userName: null,
        ipaddr: null,
        loginLocation: null,
        browser: null,
        os: null,
        status: null,
        msg: null,
        loginTime: null
      },
      form: {},
      rules: {}
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.loading = true
      getListFunc(this.queryParams).then((result) => {
        this.logininforList = result.rows
        this.total = result.total
        this.loading = false
      })
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
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
        loginTime: null
      }
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.infoId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },

    handleDrawerOpen() {
      const from = this.form
      if (from.funcPk == null) {
        this.drawerTitle = '新增功能'
      } else {
        this.drawerTitle = '修改功能'
      }
      this.table = true
    },
    submitForm() {
      if (this.loading) {
        return
      }
      this.$confirm('确定要提交表单吗？')
        .then(_ => {
          this.loading = true
          this.timer = setTimeout(() => {
            // 动画关闭需要一定的时间
            setTimeout(() => {
              this.loading = false
              this.cancel()
            }, 400)
          }, 2000)
        })
        .catch(_ => {})
    },
    cancel() {
      this.table = false
      clearTimeout(this.timer)
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
.top-right-btn {
	position: relative;
	float: right;
}
.el-drawer{
  overflow: scroll;
}
</style>
