<template>
  <div>
    <el-config-provider :locale="locale">
      <router-view></router-view>
    </el-config-provider>
  </div>
</template>
<script setup>

import {computed, ref} from "vue";
import {zhCn} from "element-plus/es/locale/index";

const language = ref('zh-cn')
const locale = computed(() => (language.value === 'zh-cn' ? zhCn : zhCn))
</script>
<style scoped lang="scss">
  .body{
    margin: 0;
    border: none;
  }
</style>
  