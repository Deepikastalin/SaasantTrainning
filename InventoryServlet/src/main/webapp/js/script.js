function fetchProduct() {
  const productId = document.getElementById("productSelect").value;
  if (!productId) return;

  const xhr = new XMLHttpRequest();
  xhr.open("GET", "ProductController?id=" + productId, true);
  xhr.onload = function () {
    if (xhr.status === 200) {
      document.getElementById("productDetails").innerHTML = xhr.responseText;
    }
  };
  xhr.send();
}
