function renderFooter() {
    document.getElementById("footer").innerHTML = `
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
                    <td class="description">Adresse: <a target="_blank" href="https://www.google.com/maps/place/N%C3%B8rre+Blvd.+98,+4600+K%C3%B8ge/@55.4643013,12.1740588,15z/data=!4m6!3m5!1s0x4652f101a86e4d1f:0x925d182a3f21ffe1!8m2!3d55.4660909!4d12.1822129!16s%2Fg%2F11csff4bzs?entry=ttu">
                    Nørre Boulevard 98, 1. tv. 4600 Køge.</a></td>
                </tr>
            </table>
        </section>
        <section id="footer_right">
            <h4 class="description">Links</h4>
            <ul id="footer_links">
                <li>
                    <a href="https://dk.linkedin.com/in/sophie-glimsager" class="footer_icon" target="_blank">
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" data-supported-dps="24x24" fill="currentColor" class="mercado-match" width="24" height="24" focusable="false">
                            <path d="M20.5 2h-17A1.5 1.5 0 002 3.5v17A1.5 1.5 0 003.5 22h17a1.5 1.5 0 001.5-1.5v-17A1.5 1.5 0 0020.5 2zM8 19H5v-9h3zM6.5 8.25A1.75 1.75 0 118.3 6.5a1.78 1.78 0 01-1.8 1.75zM19 19h-3v-4.74c0-1.42-.6-1.93-1.38-1.93A1.74 1.74 0 0013 14.19a.66.66 0 000 .14V19h-3v-9h2.9v1.3a3.11 3.11 0 012.7-1.4c1.55 0 3.36.86 3.36 3.66z"></path>
                        </svg>
                    </a>
                </li>
            </ul>
        </section>
    `;
}