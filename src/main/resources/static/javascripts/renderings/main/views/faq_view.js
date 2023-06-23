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
            <section class="chapter_caption">
                <h1 id="faq_title" class="title">Hyppigt stillede spørgsmål</h1> 
                <p class="title_description">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi culpa dignissimos, dolores libero molestiae natus omnis perspiciatis quod reiciendis. Autem blanditiis corporis earum impedit magni perferendis quo veniam voluptatum. Dicta!</p>
            </section>
            <hr />
            <div id="question_and_answers">${renderQuestionsAndAnswers()}</div>
        </section>
    `;
}