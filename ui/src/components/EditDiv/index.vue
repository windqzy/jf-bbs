<template>
  <div class="edit-div"
       v-html="innerText"
       :contenteditable="canEdit"
       @focus="getFocus"
       @blur="isLocked = false"
       @input="changeText">
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    name: 'editDiv',
    props: {
      value: {
        type: String,
        default: ''
      },
      canEdit: {
        type: Boolean,
        default: true
      }
    },
    data() {
      return {
        innerText: this.value,
        isLocked: false
      }
    },
    watch: {
      'value'() {
        if (!this.isLocked) {
          // if (!this.isLocked || !this.innerText) {
          this.innerText = this.value;
        }
      }
    },
    methods: {
      getFocus() {
        this.isLocked = true;
        this.$emit('focus', this.$el);
      },
      changeText() {
        this.$emit('input', this.$el.innerHTML);
      }
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss">
  .edit-div {
    //width: 100%;
    overflow: auto;
    word-break: break-all;
    user-select: text;
    white-space: pre-wrap;
    text-align: left;

    position: relative;
    padding: 8px 12px;
    font-size: 14px;
    line-height: 1.5;
    color: #17181a;
    outline: none;
    min-height: 16px;
    background-color: #fff;
    border: 1px solid #f1f1f1;
    border-radius: 3px;
    &[contenteditable=true] {
      user-modify: read-write-plaintext-only;
      &:empty:before {
        content: attr(placeholder);
        display: block;
        color: #ccc;
      }
    }
    &:focus {
      border: 1px solid #009688;
    }
  }
</style>
