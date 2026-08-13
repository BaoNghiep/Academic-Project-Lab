using DAL.Entities;
using DAL.Repositories;
using Microsoft.IdentityModel.Tokens;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BLL.Service
{
    public class EventService
    {
        private EventRepo _eventRepository;
        public EventService()
        {
            _eventRepository = new EventRepo();
        }

        // ham lay het thong tin Event
        public List<Event> GetEvents()
        {
            return _eventRepository.GetEvents();
        }

        // ham tim kiem theo tu khoa
        // OVERLOADING: CAC HAM GIONG TEN NHUNG KHAC THAM SO
        public List<Event> GetEvents(string keyword)
        {
            if (keyword.IsNullOrEmpty())
            {
                return GetEvents();
            }

            return _eventRepository.GetEvents(keyword);
        }

        // ham delete
        public void Delete(Event ev)
        {
            _eventRepository.DeleteEvent(ev);
        }

        // ham create
        public void Create(Event ev)
        {
            _eventRepository.CreateEvent(ev);
        }

        // ham update
        public void Update(Event ev)
        {
            _eventRepository.UpdateEvent(ev);
        }


    }
}
