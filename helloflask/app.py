from flask import Flask,request,jsonify
import numpy as np
import pickle
model = pickle.load(open('model_svm.pkl','rb'))
app = Flask(__name__)
@app.route('/')
def index():
    return "Hello world"
    # return predict()
@app.route('/predict',methods=['POST'])
def predict():
    self_esteem = request.form.get('self_esteem')
    mental_health_history = request.form.get('mental_health_history')
    headache = request.form.get('headache')
    blood_pressure = request.form.get('blood_pressure')
    sleep_quality = request.form.get('sleep_quality')
    breathing_problem = request.form.get('breathing_problem')
    noise_level = request.form.get('noise_level')
    living_conditions = request.form.get('living_conditions')
    safety = request.form.get('safety')
    basic_needs = request.form.get('basic_needs')
    academic_performance = request.form.get('academic_performance')
    study_load = request.form.get('study_load')
    teacher_student_relationship = request.form.get('teacher_student_relationship')
    future_career_concern = request.form.get('future_career_concern')
    social_support = request.form.get('social_support')
    peer_pressure = request.form.get('peer_pressure')
    extracurricular_activities = request.form.get('extracurricular_activities')
    bullying = request.form.get('bullying')
    # depression = []
    input_query = np.array([[self_esteem,mental_health_history,headache, blood_pressure, sleep_quality, breathing_problem, 
                             noise_level, living_conditions, safety, basic_needs, academic_performance, study_load, 
                             teacher_student_relationship, future_career_concern, social_support, peer_pressure, 
                             extracurricular_activities, bullying]])
    result = model.predict(input_query)[0]
    return jsonify({'placement':str(result)})
if __name__ == '__main__':
    app.run(debug=True)