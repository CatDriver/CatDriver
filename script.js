$('.carousel').carousel({
  interval: 6000
})


var counter = 1;
function addToBasket(){
  document.getElementById("cart").innerHTML = counter;
  counter++;   
  return counter;
}