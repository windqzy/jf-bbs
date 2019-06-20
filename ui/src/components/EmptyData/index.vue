<template>
  <div class="post-empty">
    <svg-icon :icon-class="icon"></svg-icon>
    <slot name="content">
      <p>暂无数据</p>
    </slot>
  </div>
</template>

<script>
  export default {
    name: "index",
    props: {
      icon: {
        type: String,
        default: 'empty'
      }
    },
    data() {
      return {

      }
    }
  }
</script>

<style scoped>

</style>
