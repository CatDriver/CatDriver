let carts = document.querySelectorAll('.add-cart');

let products = [
    {
        name: 'Product 1',
        tag:'product1',
        price:15,
        inCart:0
    },
    {
        name: 'Product 2',
        tag:'product2',
        price:25,
        inCart:0
    },
    {
        name: 'Product 3',
        tag:'product3',
        price:10,
        inCart:0
    }
];

for (let i=0; i < carts.length; i++){
    carts[i].addEventListener('click',() => {
        cartNumbers(products[i]);
        totalCost(products[i])
    })
}

function onLoadCartNumbers(){
    let productNumbers = localStorage.getItem('cartNumbers');

    if(productNumbers){
        document.querySelector('.shopping-cart span').textContent = productNumbers;
    }
}

function cartNumbers(product){
    let productNumbers = localStorage.getItem('cartNumbers');
    productNumbers = parseInt(productNumbers);
    if( productNumbers ){
        localStorage.setItem('cartNumbers', productNumbers + 1);
        document.querySelector('.shopping-cart span').textContent = productNumbers + 1;
    } else {
        localStorage.setItem('cartNumbers', 1);
        document.querySelector('.shopping-cart span').textContent = 1;
    }
    setItems(product);
}

function setItems(product){
    let cartItems = localStorage.getItem('productsInCart');
    cartItems = JSON.parse(cartItems); //Json to javascript
    if(cartItems !=null){

        if(cartItems[product.tag] == undefined){
            cartItems = {
                ...cartItems,
                [product.tag]: product
            }
        }
        cartItems[product.tag].inCart += 1;
    }else {
        product.inCart = 1;
        cartItems = {
            [product.tag]: product
        }
    }
    localStorage.setItem("productsInCart", JSON.stringify
    (cartItems));
}

function totalCost(product){
    //console.log("Product price is", product.price)
    let cartCost = localStorage.getItem('totalCost');
    console.log("Cart cost is", cartCost);
    console.log(typeof  cartCost);

    if(cartCost != null){
        cartCost = parseInt(cartCost); //parse cartCost string to cartCost int
        localStorage.setItem("totalCost", cartCost +
        product.price);
    }else{
        localStorage.setItem("totalCost", product.price);
    }

}
function displayCart(){
    let cartItems = localStorage.getItem("productsInCart");
    cartItems = JSON.parse(cartItems);
    let productContainer = document.querySelector
    (".products");
    let cartCost = localStorage.getItem('totalCost');

    console.log(cartItems);
    if( cartItems && productContainer){
        productContainer.innerHTML = '';
        Object.values(cartItems).map(item => {
            productContainer.innerHTML += `
            <div class="product">
                <ion-icon name="close-circle-outline"></ion-icon>
                <img src="./images/${item.tag}.jpg">
                <span>${item.name}</span>
            </div>
            <div class="price">$${item.price}</div>
            <div class="quantity">
            <ion-icon name="caret-back-circle-outline"></ion-icon>
            <span>${item.inCart}</span>
            <ion-icon name="caret-forward-circle-outline"></ion-icon>
            </div>
            <div class="total">$${item.inCart * item.price}</div>
            `;
        });

        productContainer.innerHTML += `
        <div class="basketTotalContainer">
            <h4 class="basketTotalTitle">
                Cart Total
                </h4>
                <h4 class="basketTotal">
                    $${cartCost}
                </h4>
        `
    }
}
onLoadCartNumbers();
displayCart();