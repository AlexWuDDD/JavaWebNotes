// function editCart(cardItemId, buyCount){
//     window.location.href='cart.do?operate=editCart&cartItemId='+cardItemId+'&buyCount='+buyCount;
// }

window.onload=function(){
    let vue = new Vue({
        el:'#cart_div',
        data:{
            cart:{}
        },
        methods:{
            getCart:function(){
                axios({
                    method:'POST',
                    url:'cart.do',
                    params:{
                        operate:'cartInfo'
                    }                    
                }).then(function(value){
                    var cart = value.data;
                    vue.cart = cart;
                    console.log(vue.cart);
                }).catch(function(err){
                    console.log(err)
                });
            },
            editCart:function(cardItemId, buyCount){
                axios({
                    method:'POST',
                    url:'cart.do',
                    params:{
                        operate:'editCart',
                        cartItemId:cardItemId,
                        buyCount:buyCount
                    }
                }).then(function(){
                    vue.getCart();
                })
            }
        },
        mounted:function(){
            this.getCart();
        }
    });
}