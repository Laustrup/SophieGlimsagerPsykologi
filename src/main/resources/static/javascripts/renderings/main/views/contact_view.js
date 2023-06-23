function renderContact() {
    document.getElementById("main").innerHTML = `
        <section id="contact_section" class="inner_container">
            <section id="contact_title_section" class="chapter_caption">
                <h1 class="title">Kontakt</h1>
                <p class="title_description">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab atque, dolore ducimus eligendi et explicabo facilis harum ipsam labore minus molestias nam non nostrum placeat recusandae reiciendis veritatis voluptate voluptatibus.</p>
            </section>
            <hr />
            <form id="contact_form">
                <div class="input_fields">
                    <p>Alle med * er påkrævet</p>
                    ${inputField({
                        id: "email_field",
                        label: "Email*",
                        type: EMAIL_INPUT,
                        placeholder: "F.eks. lars.alfred@mail.com",
                        isRequired: true
                    })}
                    ${inputField({
                        id: "subject_field",
                        label: "Emne",
                        type: TEXT_INPUT,
                        placeholder: "Vælg emne til din besked",
                        isRequired: false
                    })}
                    <div class="input_field">
                        <label for="contact_content">Besked*</label>
                        <textarea id="contact_content" rows="10" cols="33" placeholder="Uddyb din besked" required></textarea>
                    </div>
                </div>
                <div id="contact_buttons">
                    <button type="submit" class="positive_button" onclick="sendContactEmail()">Send Email</button>
                </div>
            </form>
        </section>
    `;
}

function sendContactEmail() {

}