<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="供应商" prop="distributorName">
        <el-input
          v-model="queryParams.sName"
          placeholder="请输入供应商"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="申请人" prop="proposer">
        <el-input
          v-model="queryParams.proposer"
          placeholder="请输入申请人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable :disabled="true">
          <el-option
            v-for="dict in dict.type.his_warehousing_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="warehousingUpdate3"
          v-hasPermi="['his:warehousing:edit']"
        >审核通过</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="warehousingUpdate2"
          v-hasPermi="['his:warehousing:edit']"
        >审核不通过</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" border :data="warehousingList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="单据ID" align="center" prop="billsid"  width="175"/>
      <el-table-column label="供应商" align="center" prop="hisSupplier.supplierName" width="175"/>
      <el-table-column label="采购批发总额" align="center" prop="totalMoney" width="120" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.his_warehousing_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="申请人" align="center" prop="proposer" width="100"/>
      <el-table-column label="入库操作人" align="center" prop="operator" width="100"/>
      <el-table-column label="入库时间" align="center" prop="storageTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.storageTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import { listWarehousing, getWarehousing, delWarehousing, addWarehousing, updateWarehousing,listHisMedicine } from "@/api/his/warehousing";

export default {
  name: "Audit",
  dicts: ['his_warehousing_status'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 采购入库表格数据
      warehousingList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        billsid: null,
        suppliersId: null,
        distributorName: null,
        totalMoney: null,
        status: '1',
        proposer: null,
        operator: null,
        storageTime: null,
        sName: null

      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询采购入库列表 */
    getList() {
      this.loading = false;
      listWarehousing(this.queryParams).then(response => {
        this.warehousingList = response.rows;
        this.total = response.total;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        billsid: null,
        suppliersId: null,
        totalMoney: null,
        status: null,
        proposer: null,
        operator: null,
        storageTime: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.queryParams.sName=null;
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 状态更改 */
    warehousingUpdate2(row) {
      this.reset();
      const id = row.id || this.ids
      getWarehousing(id).then(response => {
        this.form = response.data;
        if(this.form.status==3){
          this.$modal.msgWarning("审核状态更改无效");
          return;
        }
        this.form.status=3;
        updateWarehousing(this.form).then(response => {
          this.$modal.msgSuccess("审核状态更改成功");
          this.getList();
        });
      });
    },
    warehousingUpdate3(row) {
      this.reset();
      const id = row.id || this.ids
      getWarehousing(id).then(response => {
        this.form = response.data;
        if(this.form.status!=1){
          this.$modal.msgWarning("审核状态更改无效");
          return;
        }
        this.form.status=2;
        this.form.storageTime=Date.now();
        this.form.operator=this.$store.state.user.name;
        updateWarehousing(this.form).then(response => {
          this.$modal.msgSuccess("审核状态更改成功");
          this.getList();
        });
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateWarehousing(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWarehousing(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */

    handleDelete2(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除采购入库编号为"' + ids + '"的数据项？').then(function() {
        return delWarehousing(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('his/warehousing/export', {
        ...this.queryParams
      }, `warehousing_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
