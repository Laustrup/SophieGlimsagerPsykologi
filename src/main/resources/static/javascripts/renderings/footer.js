function renderFooter() {
    document.getElementById("footer_content").innerHTML = `
        <section id="footer_left">
            <table id="contact_info_footer">
                <tr class="footer_row">
                    <th class="title">Kontakt info</th>
                </tr>
                <tr class="footer_row">
                    <td class="description">Email:</td>
                </tr>
                <tr class="footer_row">
                    <td class="description">Telefon: +45 60548098</td>
                </tr>
                <tr class="footer_row">
                    <td class="description">Land: Danmark</td>
                </tr>
                <tr class="footer_row">
                    <td class="description">Adresse: <a href="https://www.google.com/maps?client=firefox-b-d&q=n%C3%B8rre+boulevard+98+k%C3%B8ge&um=1&ie=UTF-8&sa=X&ved=2ahUKEwi-8ouNl9j7AhUSXfEDHSQSDuUQ_AUoAXoECAIQAw">
                    Nørre Boulevard 98, 1. tv. 4600 Køge.</a></td>
                </tr>
            </table>
        </section>
        <section id="footer_right">
            <h4 class="description">Links</h4>
            <ul id="footer_links">
                <li>
                    <a href="https://dk.linkedin.com/in/sophie-glimsager" class="footer_icon"><img src="../static/images/linkedin.png" alt="LinkedIn"></a>
                </li>
            </ul>
        </section>
    `;
}