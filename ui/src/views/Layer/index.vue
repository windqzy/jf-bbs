<template>
  <div>
    <v-header @change="changeLabel"></v-header>
    <router-view></router-view>
  </div>
</template>

<script>
  import Header from '@/components/Header';

  export default {
    name: "index",
    components: {
      'v-header': Header
    },
    data() {
      return {

      }
    },
    methods:{
      changeLabel() {

      }
    }
  }
</script>

<style scoped>

</style>
