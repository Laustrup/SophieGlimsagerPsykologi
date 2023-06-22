async function renderFAQ() {
    const faqs = await getFAQ();

    function renderQuestionsAndAnswers() {
        let html = ``;

        if (faqs !== undefined)
            faqs.forEach((faq,index) => {
                html += `
                    <div class="question_and_answer">
                        <h3 id="question_${index}" class="question"
                            onclick="document.getElementById('answer_${index}').innerText = document.getElementById('answer_${index}').innerText === '' ? '${faq.answer}' : ''"
                        >
                            ${faq.question}
                        </h3>
                        <h4 id="answer_${index}" class="answer"></h4>
                    </div>
                `;
            });

        return html;
    }

    document.getElementById("main").innerHTML = `
        <section id="faq_section">
            <div>
                <h1 class="title">Hyppigt stillede spørgsmål<\h1>
            </div>
            <hr />
            <div id="question_and_answers">${renderQuestionsAndAnswers()}</div>
        </section>
    `;
}