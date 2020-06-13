
<!-- MultiStep Form -->

        <form id="msform" action="/client/info" method="POST">
            <!-- progressbar -->
            <ul id="progressbar">
                <li class="active">${clpersondetails}</li>
                <li>${clparam}</li>
                <li>${clnutrgoal}</li>
            </ul>
            <!-- fieldsets -->
            <fieldset>
                <h1 class="rg-h1-cl">${clpersondetails}</h1>
                <h1 class="fs-subtitle">${cllabeltell}</h1>

                <c:if test="${sessionScope.error_name}">
                    <div class="alert alert-danger">
                        <a href="#" class="close">x</a>
                        <strong>${oh}</strong> ${error_name_info}
                    </div>
                </c:if>
                <c:if test="${sessionScope.error_age}">
                    <div class="alert alert-danger">
                        <a href="#" class="close">x</a>
                        <strong>${oh}</strong> ${error_age_info}
                    </div>
                </c:if>
                <c:if test="${sessionScope.error_height}">
                    <div class="alert alert-danger">
                        <a href="#" class="close">x</a>
                        <strong>${oh}</strong> ${error_height_info}
                    </div>
                </c:if>
                <c:if test="${sessionScope.error_weight}">
                    <div class="alert alert-danger">
                        <a href="#" class="close">x</a>
                        <strong>${oh}</strong> ${error_weight_info}
                    </div>
                </c:if>
                <c:if test="${sessionScope.error == true}">
                    <div class="alert alert-danger">
                        <a href="#" class="close">x</a>
                        <strong>${oh}</strong> ${error_info}
                    </div>
                </c:if>

                <div class="rg-field-wrap">
                    <label class="rg-label">
                        ${cllabelname}<span class="req rg-span">*</span>
                    </label>
                    <input class="rg-input" type="text" name="name" required autocomplete="off"/>
                    <p class="invalid-p"></p>
                </div>
                <div class="rg-field-wrap">
                    <label class="rg-label">
                        ${cllabelage}<span class="req rg-span">*</span>
                    </label>
                    <input class="rg-input" type="text" name="age" required autocomplete="off"/>
                    <p class="invalid-p"></p>
                </div>
                <div class="rg-field-wrap">
                    <label class="rg-label">
                        ${cllabelgender}<span class="req rg-span">*</span>
                    </label>
                    <select class="rg-select" name="gender">
                        <option style="display:none">
                        <c:forEach var="gender" items="${genders}">
                            <option value="${gender.getId()}">${gender.getGender()}</option>
                        </c:forEach>
                    </select>
                    <p class="invalid-p"></p>
                </div>

                <button type="button" name="next" class="next rg-button rg-button-block">
                ${clbuttonnext}</button>
            </fieldset>
            <fieldset>
                <h1 class="rg-h1-cl">${clparam}</h1>
                <h1 class="fs-subtitle">${cllabeltell}</h1>
                <div class="rg-field-wrap">
                    <label class="rg-label">
                        ${cllabelheight}<span class="req rg-span">*</span>
                    </label>
                    <input class="rg-input" type="number" name="height" required autocomplete="off"/>
                    <p class="invalid-p"></p>
                </div>
                <div class="rg-field-wrap">
                    <label class="rg-label">
                        ${cllabelweight}<span class="req rg-span">*</span>
                    </label>
                    <input class="rg-input" type="number" name="weight" required autocomplete="off"/>
                    <p class="invalid-p"></p>
                </div>
                <div class="rg-field-wrap">
                    <label class="rg-label">
                        ${cllabelactivity}<span class="req rg-span">*</span>
                    </label>
                    <select class="rg-select" name="activity">
                        <option style="display:none">
                        <c:forEach var="activity" items="${activities}">
                            <option value="${activity.getId()}">${activity.getActivity()}</option>
                        </c:forEach>
                    </select>
                    <p class="invalid-p"></p>
                </div>
                <!--<input type="button" name="previous" class="previous action-button-previous" value="Previous"/>
                <input type="button" name="next" class="next action-button" value="Next"/>-->
                <button type="button" name="previous" class="previous rg-button rg-button-block-other">
                ${clbuttonprevious}</button>
                <button type="button" name="next" class="next rg-button rg-button-block-other">
                ${clbuttonnext}</button>
            </fieldset>
            <fieldset>
                <h1 class="rg-h1-cl">${clnutrgoal}</h1>
                <h1 class="fs-subtitle">${cllabeltell}</h1>
                <div class="rg-field-wrap">
                    <label class="rg-label">
                        ${cllabelnutrgoal}<span class="req rg-span">*</span>
                    </label>
                    <select class="rg-select" name="nutr_goal">
                        <option style="display:none">
                        <c:forEach var="nutrGoal" items="${nutritionGoals}">
                            <option value="${nutrGoal.getId()}">${nutrGoal.getGoal()}</option>
                        </c:forEach>
                    </select>
                    <p class="invalid-p"></p>
                </div>
                <button type="button" name="previous" class="previous rg-button rg-button-block-other">
                ${clbuttonprevious}</button>
                <button type="submit" name="submit" class="submit rg-button rg-button-block-other">
                ${clbuttonsubmit}</button>
            </fieldset>
        </form>


<!-- /.MultiStep Form -->