const getProductsBtn = document.getElementById("getAllProductsButton");

getProductsBtn.addEventListener('click', async() => {
    const resp = await fetch(`https://makeup-api.herokuapp.com/api/v1/products.json?brand=revlon`, {
        method: 'GET',
        mode: 'cors',
        //credentials: 'include',
    }).then((resp) => {
        if (!resp.ok) {
            throw new Error(resp.status);
        } else {
            return resp.json();
        }
    }).then((resp) => {
        console.log(resp);
        const colors = [];
        const products = [];
        for (const product of resp) {
            
            let name = product.name;
            let price = product.price;
            let imageLink = product.image_link;
            let description = product.description;
            let brand = product.brand;
            let type = product.product_type;
            let color = product.product_colors;

            if (color.length > 0) {
                //console.log(product);
                //addProduct(name.trim(), price, imageLink, desctription, brand, type, color);
                //console.log(color[0].colour_name)

                // let productColor = {
                //         "colorHexValue":`${color[0].hex_value}`,
                //         "colorName":`${color[0].colour_name}`
                // }

                let product = {
                    "productName":`${name}`,
                    "productPrice":`${price}`,
                    "image_Link":`${imageLink}`,
                    "productDescription":`${description.trim().substring(0, 250)}`,
                    "productBrand":{
                        "productBrandId":1,
                        "productBrand":`${brand}`
                    },
                    "productType":{
                        "productType":`${type}`
                    },
                    "productColor":{
                        "colorHexValue":`${color[0].hex_value}`
                    }
                }
                addProduct(product);
            
                //colors.push(productColor);
                //products.push(product);
                
            }
            
        }
        //console.log(products);
        //addProducts(products)
        //console.log(colors);
        //addProductColors(colors);
    }).catch((error) => {
        console.error(error);
    })
});

async function addProductColors(colors) {
    const resp = await fetch(`http://localhost:8080/api/util-products/add-colors`, {
        method:'POST',
        mode:'cors',
        headers: {
            'content-type':'application/json'
        },
        body: JSON.stringify(colors)
    })
}

async function addProducts(products) {
    const resp = await fetch(`http://localhost:8080/api/products/populate-products`, {
        method: 'POST',
        mode: 'cors',
        //credentials: 'include',
        headers: {
            'content-type':'application/json'
        },
        body: JSON.stringify(products)
    }).then((resp) => {
        if (!resp.ok) {
            throw new Error(resp.status);
        } else {
            return resp.json();
        }
    }).then((resp) => {
        console.log(resp);
    }).catch((error) => {
        console.error(error);
    })
};

async function addProduct(product) {
    console.log(`${product.productColor.colorHexValue}`);
    const resp = await fetch(`http://localhost:8080/api/products/add-product?type=${product.productType.productType}&brand=${product.productBrand.productBrand}&color=${product.productColor.colorHexValue.substring(1)}`, {
        method: 'POST',
        mode: 'cors',
        //credentials: 'include',
        headers: {
            'content-type':'application/json'
        },
        body: JSON.stringify(product)
    }).then((resp) => {
        if (!resp.ok) {
            throw new Error(resp.status);
        } else {
            return resp.json();
        }
    }).then((resp) => {
        console.log(resp);
    }).catch((error) => {
        console.error(error);
    })
};
