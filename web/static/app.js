var cart = [];

function init() {
    const cartstored = localStorage.getItem('mycart');
    if (cartstored) {
        cart = JSON.parse(cartstored);
        document.getElementById('cartcount').innerHTML = cart.length;
    }
}
init();

function savecart() {
    const cartstored = localStorage.setItem('mycart', JSON.stringify(cart));
    document.getElementById('cartcount').innerHTML = cart.length;
}

function clearcart() {
    cart = [];
    savecart();
}

function addtocart(id, title, description, price) {
    const index = cart.findIndex((element) => element.id === id);
    if (index === -1) {
        cart.push({
            id: id,
            title: title,
            description: description,
            qt: 1,
            price: price
        });
    } else {
        cart[index].qt += 1;
    }
    savecart();
    showmodal();
}

function removefromcart(index) {
    cart.splice(index, 1);
    savecart();
    generatecarthtml();
}


function addqt(index) {
    cart[index].qt += 1;
    savecart();
    generatecarthtml();
}



function minusqt(index) {
    if (cart[index].qt === 1) {
        return;
    }
    cart[index].qt -= 1;
    savecart();
    generatecarthtml();
}


function showmodal() {
    document.getElementById('added').style.display = "block";
}

function hidemodal() {
    document.getElementById('added').style.display = "none";
}

function generatecarthtml() {
    let html = '';
    let inputs = '';
    let total = 0;
    let count = 0;
    cart.forEach((item, index) => {
        count += item.qt;
        total += (item.price * item.qt);
        html += `<div class="card mb-3">
                  <div class="card-body">
                    <div class="d-flex justify-content-between">
                      <div class="d-flex flex-row align-items-center">
                        <div>
                          <img
                            src="/ImageServlet?id=${item.id}"
                            class="rounded-3" alt="Shopping item"  height="90px" style="object-fit: scale-down;"  style="width: 90px;">
                        </div>
                        <div class="ms-3">
                          <h5>${item.title}</h5>
                          <p class="small mb-0">${item.description}</p>
                        </div>
                      </div>
                      <div class="d-flex flex-row align-items-center">
                        <div style="width: 100px;">
                          <h5 class="fw-normal mb-0"> <button onclick="minusqt(${index})" class="btn btn-sm btn-success">-</button> ${item.qt} <button onclick="addqt(${index})" class="btn btn-sm btn-success">+</button> </h5>
                        </div>
                        <div style="width: 120px;">
                          <h5 class="mb-0">${item.price * item.qt}DZD</h5>
                        </div>
                        <button  class="btn btn-sm btn-danger" onclick="removefromcart(${index})">X</button>
                      </div>
                    </div>
                  </div>
                </div>`;
        inputs += `<input type="hidden" name="productids" value="${item.id}" />
                   <input type="hidden" name="productqts" value="${item.qt}" />
                   <input type="hidden" name="productprices" value="${item.price}" />`;

    });
    
    document.getElementById('totalinput').value = total;
    document.getElementById('totalPrice').innerHTML = total + ' DZD';
    document.getElementById('cartItems').innerHTML = html;
    document.getElementById('hiddencartitems').innerHTML = inputs;
    document.getElementById('cartItemsCount').innerHTML = count;
}