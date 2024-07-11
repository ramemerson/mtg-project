function selectCardLeft(element) {
    var selectedCard = document.querySelector('.cardColumn.selected-left');
    if (selectedCard) {
        selectedCard.classList.remove('selected-left');
    }

    element.classList.add('selected-left');

    var cardId = element.getAttribute('data-card-id');
    var cardName = element.getAttribute('data-card-name');
    var cardImageUrl = element.getAttribute('data-card-image-url');
    var cardPrice = element.getAttribute('data-price');
    var cardRarity = element.getAttribute('data-rarity');

    // Update the selected card area with the details of the selected card
    var selectedCardArea = document.getElementById('selectedCardAreaLeft');
    selectedCardArea.innerHTML = `
    <div class="innerContainerLeft">
    <h5>${cardName}</h5>
    <img src="${cardImageUrl}" alt="Selected Card Image">
    <p style="color: yellow; font-size: 18px ">Price (€): ${cardPrice}</p>
    <p style="color: yellow; font-size: 18px">Rarity: ${cardRarity}</p>
    </div>
`;
    selectedCardArea.style.opacity = '1';
}

function selectCardRight(element) {
    var selectedCard = document.querySelector('.cardColumn.selected-right');
    if (selectedCard) {
        selectedCard.classList.remove('selected-right');
    }

    element.classList.add('selected-right');

    var cardId = element.getAttribute('data-card-id');
    var cardName = element.getAttribute('data-card-name');
    var cardImageUrl = element.getAttribute('data-card-image-url');
    var cardPrice = element.getAttribute('data-price');
    var cardRarity = element.getAttribute('data-rarity');

    // Update the selected card area with the details of the selected card
    var selectedCardArea = document.getElementById('selectedCardAreaRight');
    selectedCardArea.innerHTML = `
    <div class="innerContainerRight">
    <h5>${cardName}</h5>
    <img src="${cardImageUrl}" alt="Selected Card Image">
    <p style="color: yellow; font-size: 18px ">Price (€): ${cardPrice}</p>
    <p style="color: yellow; font-size: 18px">Rarity: ${cardRarity}</p>
    </div>
`;
    selectedCardArea.style.opacity = '1';
}


function selectUser(element) {
    var selectedUser = document.querySelector('.userBox.selected');

    if (selectedUser) {
        selectedUser.classList.remove('selected');
    }
    element.classList.add('selected');

    var userId = element.getAttribute('data-user-id');
    var userName = element.innerText;

    fetch(`/trade/${userId}/cards`)
        .then(Response => Response.json())
        .then(userCards => {
            var userCardsArea = document.getElementById('userCardsArea');
            userCardsArea.innerHTML = '';
            userCards.forEach(card => {
                userCardsArea.innerHTML += `
                    <div class="cardColumn" data-card-id="${card.id}" data-card-name="${card.name}" data-card-image-url="${card.image_uris.small}" data-price="${card.prices.eur}" data-rarity="${card.rarity}" onclick="selectCardRight(this)">
                        <img src="${card.image_uris.small}" alt="Card Image">
                    </div>
                `;
            })

            var selectedUserName = document.getElementById('selectedUserName');
            selectedUserName.innerText = userName;

            var userList = document.getElementById('userList');
            userList.style.display = 'none';
            var userCardsContainer = document.getElementById('userCardsContainer');
            userCardsContainer.style.display = 'block';
        })
        .catch(error => console.error('Error fetching user cards:', error));

    var userCardsArea = document.getElementById('userCardsArea');
    userCardsArea.innerHTML = `

    `;
}

function backToUserList() {
    var userCardsContainer = document.getElementById('userCardsContainer');
    userCardsContainer.style.display = 'none';

    var userList = document.getElementById('userList');
    userList.style.display = 'block';
}