<template>
  <div>
    <v-header @change="changeLabel"></v-header>
    <router-view></router-view>
    <v-footer></v-footer>
  </div>
</template>

<script>
  import Header from '@/components/Header';
  import Footer from '@/components/Footer';

  export default {
    name: "index",
    components: {
      'v-header': Header,
      'v-footer': Footer,
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
